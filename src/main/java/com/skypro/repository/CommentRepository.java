package com.skypro.repository;

import com.skypro.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий для работы с сущностью {@link CommentEntity}.
 * <p>
 * Предоставляет стандартные CRUD-методы, унаследованные от {@link JpaRepository},
 * а также дополнительные методы для поиска комментариев по объявлению.
 * </p>
 */
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    /**
     * Находит все комментарии, относящиеся к объявлению с указанным ID.
     *
     * @param adId идентификатор объявления
     * @return список комментариев {@link CommentEntity} для данного объявления
     */
    List<CommentEntity> findByAdId(Integer adId);
}
