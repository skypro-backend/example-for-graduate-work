package ru.skypro.homework.service;

import ru.skypro.homework.entity.Advertisement;

public interface AdvertisementService {
    void create(Advertisement advertisement);
    void update(Advertisement advertisement);
    void delete(Advertisement advertisement);
}
