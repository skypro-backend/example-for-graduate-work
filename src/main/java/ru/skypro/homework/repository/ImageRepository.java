package ru.skypro.homework.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.homework.entity.Image;


public interface ImageRepository extends CrudRepository<Image, String> {
}
