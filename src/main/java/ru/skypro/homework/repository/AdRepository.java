package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.AdEntity;

import java.util.List;

/**
 * Репозиторий для работы с сущностью {@link AdEntity}.
 * Предоставляет методы для доступа к данным объявлений в базе данных.
 * Основные функции:
 * - стандартные CRUD-методы (наследуются от JpaRepository);
 * - поиск объявлений по ID автора.
 */
public interface AdRepository extends JpaRepository<AdEntity, Integer> {

    /**
     * Найти все объявления по ID автора.
     * @param authorId ID автора объявлений
     * @return список объявлений, созданных указанным пользователем
     */
    List<AdEntity> findByAuthorId(Integer authorId);
}
