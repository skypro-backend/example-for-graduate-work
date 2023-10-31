package ru.skypro.homework.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.homework.entity.ImageAd;

public interface ImageAdRepository extends CrudRepository<ImageAd, String> {
}
