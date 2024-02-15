package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;

import java.util.List;
import java.util.Optional;

@Service
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;

    @Autowired
    public AdServiceImpl(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public List<Ad> getAllAds() {
        return adRepository.findAll();
    }

    @Override
    public Optional<Ad> getAdById(Long id) {
        return adRepository.findById(id);
    }

    @Override
    public Ad createAd(Ad ad) {
        return adRepository.save(ad);
    }

    @Override
    public Ad updateAd(Long id, Ad ad) {
        Ad existingAd = getAdById(id).orElseThrow(() -> new RuntimeException("Ad not found with id: " + id));
        existingAd.setTitle(ad.getTitle());
        existingAd.setDescription(ad.getDescription());
        existingAd.setPrice(ad.getPrice());
        return adRepository.save(existingAd);
    }

    @Override
    public void deleteAd(Long id) {
        adRepository.deleteById(id);
    }
}
