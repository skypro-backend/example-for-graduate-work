package ru.skypro.homework.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;

public interface ImageRepository extends CrudRepository<Image, Integer> {
}
