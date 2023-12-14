package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.service.AdService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AdServiceImpl implements AdService {
    @Override
    public List<Ad> findAll() {
        return null;
    }

    @Override
    public Ad findById(Long id) {
        return null;
    }

    @Override
    public List<Ad> getAdByAuthUser() {
        return null;
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
    public void deleteAd(Ad ad) {

    }
}
