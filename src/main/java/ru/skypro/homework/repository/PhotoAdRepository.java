package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.PhotoAd;

import java.util.Optional;

public interface PhotoAdRepository extends JpaRepository<PhotoAd, Long> {
    Optional<PhotoAd> findPhotoAdByAd_Id(Long id);
}
