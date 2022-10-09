package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.homework.entity.Image;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    @Query(value = "SELECT * FROM ads_images WHERE id_ads = ?1", nativeQuery = true)
    public List<Image> findAllByAdsId(Integer id);

    @Query(value = "DELETE FROM ads_images WHERE id_ads = ?1", nativeQuery = true)
    int deleteByAdsId(Integer id);
}
