package ru.skypro.homework.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}