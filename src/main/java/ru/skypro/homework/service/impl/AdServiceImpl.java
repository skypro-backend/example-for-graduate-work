package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.service.AdService;

import java.util.Collection;
import java.util.Optional;

@Service
public class AdServiceImpl implements AdService {
    @Override
    public Collection<Ad> findAll() {
        return null;
    }

    @Override
    public Optional<Ad> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Ad> getAdByAuthUser() {
        return Optional.empty();
    }

    @Override
    public void addAd(CreateOrUpdateAdDTO ad, byte[] img) {
    }

    @Override
    public void updateAd(CreateOrUpdateAdDTO ad) {
    }

    @Override
    public void updateImage(byte[] img) {

    }

    @Override
    public void deleteAd(Optional<Ad> ad) {

    }
}
