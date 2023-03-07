package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.PosterEntity;

@Repository
public interface PosterRepository extends JpaRepository<PosterEntity, Long> {
    PosterEntity findByAdsId(Long adsId);
}
