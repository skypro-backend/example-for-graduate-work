package ru.skypro.homework.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.homework.entity.ImageAd;
/**
 * The repository for getting methods to work with ad's image database
 * @author Sulaeva Marina
 */
public interface ImageAdRepository extends CrudRepository<ImageAd, String> {
}
