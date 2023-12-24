package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.Images;

public interface ImagesRepository extends JpaRepository<Images, Long> {
}
