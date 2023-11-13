package ru.skypro.homework.service;

import ru.skypro.homework.entity.Ad;

public interface AdService {
    void create(Ad ad);
    void update(Ad ad);
    void delete(Ad ad);
}
