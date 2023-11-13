package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;

@Service
@AllArgsConstructor
public class AdServiceImpl implements AdService {
    private final AdRepository repository;

    @Override
    public void create(Ad ad) {
        repository.save(ad);
    }

    @Override
    public void update(Ad ad) {
        repository.delete(ad);
        repository.save(ad);
    }

    @Override
    public void delete(Ad ad) {
        repository.delete(ad);
    }
}
