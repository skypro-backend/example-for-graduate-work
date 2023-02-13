package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.dto.AdsDto;
import ru.skypro.homework.model.entity.Ads;
import ru.skypro.homework.model.entity.Image;
import ru.skypro.homework.model.mapper.AdsMapper;
import ru.skypro.homework.model.repository.AdsRepository;
import ru.skypro.homework.model.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.util.Optional;
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
    public String updateAdsImage(Integer id,MultipartFile image) {
        Image imageToAds = new Image();
        Ads ads = getImageFromAds(id);
        try {
            byte[] bytes = image.getBytes();
            imageToAds.setData(bytes);
            imageToAds.setGeneratedIdFromMultipartFile(UUID.randomUUID().toString());
            imageToAds.setFileSize(imageToAds.getFileSize());
            imageToAds.setMediaType(imageToAds.getMediaType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ads.getImages().add(imageToAds);
            return imageToAds.getGeneratedIdFromMultipartFile();
    }
    public Ads getImageFromAds(Integer id) {
        return adsRepository.findById(id).orElse(null);
    }

}
