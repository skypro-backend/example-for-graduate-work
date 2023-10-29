package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.Image;

import java.awt.*;


public interface ImageRepository extends JpaRepository<Image, Integer> {

}
