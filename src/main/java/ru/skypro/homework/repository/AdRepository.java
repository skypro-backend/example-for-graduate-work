package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

import java.util.List;

/**
 * Репозиторий для работы с объявлениями.
 * Предоставляет методы для доступа к данным объявлений в базе данных.
 */
@Repository
public interface AdRepository extends JpaRepository<AdEntity, Integer> {

    /**
     * Находит все объявления, созданные указанным пользователем.
     *
     * @param author пользователь-автор
     * @return список объявлений пользователя
     */
    List<AdEntity> findByAuthor(UserEntity author);

    /**
     * Находит все объявления по ID автора.
     *
     * @param authorId ID автора
     * @return список объявлений автора
     */
    List<AdEntity> findByAuthorId(Integer authorId);
}
