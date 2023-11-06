package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.models.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
