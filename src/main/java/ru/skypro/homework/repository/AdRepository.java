package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.AdEntity;

import java.util.List;

/**
 * Репозиторий для взаимодействия с сущностями объявлений {@link AdEntity} в БД.
 */
@Repository
public interface AdRepository extends JpaRepository<AdEntity, Integer> {
    /**
     * Возвращает все объявления, созданные конкретным автором.
     *
     * @param authorId уникальный идентификатор автора (пользователя)
     * @return список сущностей объявлений
     */
    List<AdEntity> findAllByAuthorId(Integer authorId);
}