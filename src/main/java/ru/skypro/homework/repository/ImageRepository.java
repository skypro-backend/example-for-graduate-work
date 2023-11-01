package ru.skypro.homework.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.homework.entity.Image;

/**
 * The repository for getting methods to work with user's image database
 * @author Sulaeva Marina
 */
public interface ImageRepository extends CrudRepository<Image, String> {
}
