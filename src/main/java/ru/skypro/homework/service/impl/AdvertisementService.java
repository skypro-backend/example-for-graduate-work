package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.Advertisement;
import ru.skypro.homework.repository.AdvertisementRepository;

import javax.transaction.Transactional;

@Service
public class AdvertisementService {
    @Autowired
    private AdvertisementRepository repository;

    public void  create(Advertisement advertisement) {
        repository.save(advertisement);
    }

    public void update(Advertisement advertisement) {
        repository.save(advertisement);
    }

    public void delete(Advertisement advertisement) {
        repository.delete(advertisement);
    }
}
