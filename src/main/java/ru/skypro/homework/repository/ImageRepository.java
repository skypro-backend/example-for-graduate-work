package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.ImageEntity;

/**
 * Репозиторий для работы с сущностью {@link ImageEntity}.
 * Предоставляет методы для доступа к данным изображений в базе данных.
 * Основные функции:
 * - стандартные CRUD-методы (наследуются от JpaRepository).
 */
public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {
}
