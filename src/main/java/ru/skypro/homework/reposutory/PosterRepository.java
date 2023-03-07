package ru.skypro.homework.reposutory;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.PosterEntity;

public interface PosterRepository extends JpaRepository<PosterEntity, Long> {
    PosterEntity findByAdsId(Long adsId);
}
