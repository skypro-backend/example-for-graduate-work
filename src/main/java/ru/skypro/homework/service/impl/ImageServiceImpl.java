package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.dto.AdsDto;
import ru.skypro.homework.model.entity.Image;
import ru.skypro.homework.model.mapper.AdsMapper;
import ru.skypro.homework.model.repository.AdsRepository;
import ru.skypro.homework.model.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    private final AdsRepository adsRepository;

    public ImageServiceImpl(ImageRepository imageRepository, AdsRepository adsRepository) {
        this.imageRepository = imageRepository;
        this.adsRepository = adsRepository;
    }

    @Override
    public String saveImage(MultipartFile image, AdsDto adsDto) {
        Image imageToAds = new Image();
        try {
            // создали сущность Image и указали к какому Ads она прикреплена
            byte[] bytes = image.getBytes();
            imageToAds.setData(bytes);
            imageToAds.setGeneratedIdFromMultipartFile(UUID.randomUUID().toString());
            imageToAds.setAds(AdsMapper.INSTANCE.adsDtoToAds(adsDto));
            imageToAds.setFileSize(imageToAds.getFileSize());
            imageToAds.setMediaType(imageToAds.getMediaType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
          adsDto.getImage().add(imageToAds);
            return imageToAds.getGeneratedIdFromMultipartFile();
    }

    @Override
    public byte[] getImage(Integer id) {
        adsRepository.findById(id);
        return new byte[0];
    }

}
