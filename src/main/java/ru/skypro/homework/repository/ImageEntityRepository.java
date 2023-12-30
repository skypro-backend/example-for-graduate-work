package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.ImageEntity;

import java.util.Optional;

public interface ImageEntityRepository extends JpaRepository<ImageEntity,Long > {

    Optional<ImageEntity> findByFilePath(String filePath);

    Optional<ImageEntity> findById(Integer id);
}
