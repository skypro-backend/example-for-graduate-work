package ru.skypro.homework.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.PhotoAd;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.PhotoAdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Slf4j
@Service
public class AdServiceImpl implements AdService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final UserServiceImpl userService;
    private final PhotoAdRepository photoAdRepository;
    private final AdRepository adRepository;
    @Value("${path.to.photos.folder}")
    private String photoDir;

    public AdServiceImpl(CommentRepository commentRepository, UserRepository userRepository, UserServiceImpl userService, PhotoAdRepository photoAdRepository, AdRepository adRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.photoAdRepository = photoAdRepository;
        this.adRepository = adRepository;
    }

    @Override
    public AdsDTO getAllAds() {
        AdsDTO adsDTO = new AdsDTO();
        List<AdDTO> result = new ArrayList<>();
        adRepository.findAll().forEach(u -> result.add(AdMapper.INSTANCE.adToAdDTO(u)));
        adsDTO.setResults(result);
        adsDTO.setCount(result.size());
        return adsDTO;
    }

    @Override
    public AdDTO addAd(CreateOrUpdateAdDTO createOrUpdateAdDTO, MultipartFile image, String userName) throws IOException {
        User user = userService.getCurrentUser(userName);
        Ad ad = AdMapper.INSTANCE.createOrUpdateAdDTOToAd(createOrUpdateAdDTO, user);
        ad.setAuthor(user);
        ad.setId(null);
        Path filePath = Path.of(photoDir, createOrUpdateAdDTO.getTitle() + "." + getExtension(Objects.requireNonNull(image.getOriginalFilename())));
        PhotoAd photoAd = new PhotoAd();
        photoAd.setFilePath(filePath.toString());
        photoAd.setFileSize(image.getSize());
        photoAd.setMediaType(image.getContentType());
        photoAd = photoAdRepository.save(photoAd);
        uploadPhotoAdd(filePath,image);
        ad.setImage(String.valueOf(filePath));
        ad.setPhotoAd(photoAd);
        adRepository.save(ad);
        return AdMapper.INSTANCE.adToAdDTO(ad);
    }

    @Override
    public ExtendedAdDTO getAdInfo(Long adId) {
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        User user = userRepository.findById(ad.getAuthor().getId()).orElseThrow(UserNotFoundException::new);
        return AdMapper.INSTANCE.toExtendedAdDTO(ad, user);
    }

    @Override
    public Void deleteAd(Long adId) {
        adRepository.deleteById(adId);
        return null;
    }

    @Override
    public AdDTO patchAd(Long adId, CreateOrUpdateAdDTO createOrUpdateAdDTO) {
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        ad.setTitle(createOrUpdateAdDTO.getTitle());
        ad.setPrice(createOrUpdateAdDTO.getPrice());
        ad.setDescription(createOrUpdateAdDTO.getDescription());
        return AdMapper.INSTANCE.adToAdDTO(adRepository.save(ad));
    }

    @Override
    public AdsDTO getAllAdsByAuthor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userRepository.findByEmail(name);
        List<AdDTO> result = new ArrayList<>();
        adRepository.findAllByAuthor(user).forEach(u -> result.add(AdMapper.INSTANCE.adToAdDTO(u)));
        AdsDTO adsDTO = new AdsDTO();
        adsDTO.setResults(result);
        adsDTO.setCount(result.size());
        return adsDTO;
    }

    @Override
    public String patchAdImage(Long adId, MultipartFile image) throws IOException {
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        Path filePath = Path.of(photoDir, ad.getTitle() + "."
                + getExtension(Objects.requireNonNull(image.getOriginalFilename())));
        uploadPhotoAdd(filePath, image);
        ad.setImage(String.valueOf(filePath));
        return adRepository.save(ad).getImage();
    }

    @Override
    public CommentsDTO getComments(Long adId) {
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        User user = ad.getAuthor();

        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment : commentRepository.findAllByAd(ad)) {
            commentDTOList.add(CommentMapper.INSTANCE.toCommentDTO(comment, user));
        }
        CommentsDTO commentsDTO = new CommentsDTO();
        commentsDTO.setResults(commentDTOList);
        commentsDTO.setCount(commentDTOList.size());
        return commentsDTO;
    }

    @Override
    public CommentDTO addComment(Long adId, CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        User user = ad.getAuthor();
        Comment comment = new Comment();
        comment.setAd(ad);
        comment.setAuthor(user);
        comment.setText(createOrUpdateCommentDTO.getText());
        comment.setCreatedAt(System.currentTimeMillis());
        return CommentMapper.INSTANCE.toCommentDTO(commentRepository.save(comment), user);

    }

    @Override
    public Void deleteComment(Long adId, Long commentId) {
        commentRepository.deleteById(commentId);
        return null;
    }

    @Override
    public CommentDTO patchComment(Long adId, Long commentId, CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        User user = adRepository.findById(adId).orElseThrow(AdNotFoundException::new).getAuthor();
        comment.setText(createOrUpdateCommentDTO.getText());

        return CommentMapper.INSTANCE.toCommentDTO(commentRepository.save(comment), user);
    }

    public void uploadPhotoAdd(Path filePath, MultipartFile image) throws IOException {
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream is = image.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }

    }

    public PhotoAd findPhotoAd(Long adId) {
        return photoAdRepository.findPhotoAdByAd_Id(adId).orElse(new PhotoAd());
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private Optional<Path> findFirst(Ad ad, String photoDirectory) throws IOException {
        Optional<Path> foundFile;
        File rootDirectory = new File(photoDirectory);
        String filePath = "Фото к объявлению с id=" +
                "." + ad.getId();
        try (Stream<Path> walkStream = Files.walk(rootDirectory.toPath())) {
            foundFile = walkStream.filter(p -> p.toFile().isFile())
                    .filter(p -> p.toString().endsWith(filePath))
                    .findFirst();
        }

        if (foundFile.isPresent()) {
            System.out.println(foundFile.get());
        } else {
            System.out.println("File not found");
        }
        return foundFile;
    }

}
