package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.ImageEntity;

public interface ImageEntityRepository extends JpaRepository<ImageEntity,Long > {
}
