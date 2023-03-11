package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.PosterEntity;
import java.util.Optional;

@Repository
public interface PosterRepository extends JpaRepository<PosterEntity, Integer> {
    Optional<PosterEntity> findByAdsId(Integer adsId);
}
