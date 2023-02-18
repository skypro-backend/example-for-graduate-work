package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    private AdsService adsService;

    @Override
    public void uploadImage(Integer id) {
//        log.info(FormLogInfo.getInfo());
//        AdsDTO adsDTO = adsService.getAds(id);
//        Path filePath = Path.of(petDir, petId + "." + getExtension(file.getOriginalFilename()));
//        Files.createDirectories(filePath.getParent());
//        Files.deleteIfExists(filePath);
//
//        try (InputStream is = file.getInputStream();
//             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
//             BufferedInputStream bis = new BufferedInputStream(is, 1024);
//             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
//        ) {
//            bis.transferTo(bos);
//        }
//        Pet pet = petMapper.toEntity(petRecord);
//        pet.setFilePath(filePath.toString());
//        pet.setFileSize(file.getSize());
//        pet.setMediaType(file.getContentType());
//        pet.setPhoto(file.getBytes());
//        petRepository.save(pet);

    }
}
