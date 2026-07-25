package com.skypro.repository;

import com.skypro.entity.AdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий для работы с сущностью {@link AdEntity}.
 * <p>
 * Предоставляет стандартные CRUD-методы, унаследованные от {@link JpaRepository},
 * а также дополнительные методы для поиска объявлений по автору.
 * </p>
 */

public interface AdRepository extends JpaRepository<AdEntity, Integer> {

    /**
     * Находит все объявления, созданные пользователем с указанным ID.
     *
     * @param authorId идентификатор автора (пользователя)
     * @return список объявлений {@link AdEntity}, принадлежащих автору
     */
    List<AdEntity> findAllByAuthorId(Integer authorId);
}
