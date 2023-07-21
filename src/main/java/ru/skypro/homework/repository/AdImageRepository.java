package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.service.impl.AdsImage;

public interface AdImageRepository extends JpaRepository <AdsImage, Integer> {
    String findAdImageByImageAddress(String imageAddress);
}
