package ru.skypro.homework.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.service.entities.ImageIEntity;

public interface ImageRepository extends JpaRepository<ImageIEntity, Integer> {
}
