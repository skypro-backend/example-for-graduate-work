package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Image;

import java.util.Optional;

@Repository
public interface ImageRepo extends JpaRepository<Image, Integer> {

    Optional<Image> findByUserId(int userId);

    Optional<Image> findByAdId(int adId);

    @Query(value = "SELECT file_path FROM images WHERE id = :id", nativeQuery = true)
    String findFilePathById(int id);
}
