package ru.skypro.homework.service.ads;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.projection.CreateOrUpdateAd;
import ru.skypro.homework.repository.AdRepository;

@AllArgsConstructor
@Service
public class AdsServiceImpl implements AdsService{

    private final AdRepository adRepository;

    @Override
    public ResponseEntity<?> getAllAds() {
        return null;
    }

    @Override
    public ResponseEntity<?> createAd(CreateOrUpdateAd properties, MultipartFile image) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAdFullInfo(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteAd(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateAd(Integer id, CreateOrUpdateAd properties) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllAdsByUser() {
        return null;
    }

    @Override
    public ResponseEntity<?> updateImage(Integer id, MultipartFile image) {
        return null;
    }
}
