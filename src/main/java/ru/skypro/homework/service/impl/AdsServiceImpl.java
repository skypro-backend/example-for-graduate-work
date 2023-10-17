package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {
    private final AdsService adsService;
    private final ImageService imageService;
    private final AdsRepository adsRepository;
    @Override
    public ResponseEntity<?> updateImage(Integer idPk, MultipartFile image) {
        Optional<Ad> ifThereIsAd = adsRepository.findById(idPk);

        if (ifThereIsAd.isEmpty()) {
            log.error("Ad not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Ad foundedAd = ifThereIsAd.get();

            if (foundedAd.getAuthor() != null) {
                byte[] img = new byte[0];
                try {
                    img = imageService.updateAdImage(idPk, image);
                } catch (IOException e) {
                    log.error("Image not uploaded");
                }
                return new ResponseEntity<>(img, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
    }
}
