package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.Advertisement;
import ru.skypro.homework.repository.AdvertisementRepository;
import ru.skypro.homework.service.AdvertisementService;

@Service
@AllArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {
    private final AdvertisementRepository repository;

    @Override
    public void create(Advertisement advertisement) {
        repository.save(advertisement);
    }


    @Override
    public void update(Advertisement advertisement) {
        repository.delete(advertisement);
        repository.save(advertisement);
    }

    @Override
    public void delete(Advertisement advertisement) {
        repository.delete(advertisement);
    }
}
