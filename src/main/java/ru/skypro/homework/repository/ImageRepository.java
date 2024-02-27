package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.entity.ImageEntity;

import java.util.Optional;

@Repository
@Transactional
public interface ImageRepository extends JpaRepository<ImageEntity,Long> {
    Optional<ImageEntity> findByUserId(Long id);
    Optional<ImageEntity> findByFilePath(String filePath);
}
