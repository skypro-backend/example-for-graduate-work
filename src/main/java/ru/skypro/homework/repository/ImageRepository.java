package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.models.entity.Images;

public interface ImageRepository  extends JpaRepository<Images, Integer> {

}
