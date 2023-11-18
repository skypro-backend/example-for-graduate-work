package ru.skypro.homework.service.impl;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.ImageService;

public class ImageServiceImpl implements ImageService {
    @Override
    public void uploadUserImage(MultipartFile file) {

    }

    @Override
    public byte[] uploadAdsImage(long adsId, MultipartFile file) {
        return new byte[0];
    }

    @Override
    public byte[] getImage(long id) {
        return new byte[0];
    }
}
