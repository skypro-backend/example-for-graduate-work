package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.AdsImage;


@Repository
public interface AdsImageRepository extends JpaRepository <AdsImage, Integer> {
    String findAdsImageByImageAddress(String imageAddress);
}
