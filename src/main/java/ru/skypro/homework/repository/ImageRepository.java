package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.entity.ImageEntity;
/**
 * Репозиторий для фото
 */
@Repository
@Transactional
public interface ImageRepository  extends JpaRepository<ImageEntity, Long> {

}
