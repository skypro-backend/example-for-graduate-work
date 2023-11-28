package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.exception.PhotoAdNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.PhotoAd;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.PhotoAdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.utils.MethodLog;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
    private final PhotoAdRepository photoAdRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    @Value(value = "${path.to.images.folder}")
    private String photoDir;

    public ImageServiceImpl(PhotoAdRepository photoAdRepository, AdRepository adRepository, UserRepository userRepository) {
        this.photoAdRepository = photoAdRepository;
        this.adRepository = adRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<byte[]> getImage(Long id) throws IOException {
        PhotoAd photoAd = photoAdRepository.findById(id).orElseThrow(PhotoAdNotFoundException::new);
        byte[] imageBytes = readImageBytes(photoAd.getFilePath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(photoAd.getMediaType()));
        headers.setContentLength(imageBytes.length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(imageBytes);
    }

    private static byte[] readImageBytes(String imagePath) throws IOException {
        Path path = Paths.get(imagePath);
        return Files.readAllBytes(path);
    }

    @Override
    public AdDTO addPhotoAd(CreateOrUpdateAdDTO createOrUpdateAdDTO, MultipartFile image) {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        Ad ad = AdMapper.INSTANCE.createOrUpdateAdDTOToAd(createOrUpdateAdDTO, user);
        ad.setAuthor(user);
        Image imageModel = new Image();
        try {
            imageModel.setImage(image.getBytes());
            imageModel.setAd(ad);
            imageModel.setAuthor(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ad.setImage("/"+photoDir+"/" + imageModel.getId());

        return AdMapper.INSTANCE.adToAdDTO(adRepository.save(ad));
    }





}
