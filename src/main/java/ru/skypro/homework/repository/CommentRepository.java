package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.CommentEntity;

import java.util.List;
/**
 * Интерфейс для работы с комментариями в базе данных.
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    /**
     * Возвращает комментарии, относящиеся к объявлению.
     *
     * @param adId идентификатор объявления
     * @return список комментариев объявления
     */
    List<CommentEntity> findByAdId(Integer adId);
}
