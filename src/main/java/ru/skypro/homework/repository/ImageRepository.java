package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import ru.skypro.homework.model.Image;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
