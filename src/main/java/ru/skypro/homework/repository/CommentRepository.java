package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.CommentEntity;

import java.util.List;

/**
 * Репозиторий для работы с комментариями.
 * Предоставляет методы для доступа к данным комментариев в базе данных.
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    /**
     * Находит все комментарии к указанному объявлению.
     *
     * @param adId ID объявления
     * @return список комментариев к объявлению
     */
    List<CommentEntity> findByAdId(Integer adId);

    /**
     * Удаляет комментарий по ID объявления и ID комментария.
     *
     * @param adId ID объявления
     * @param commentId ID комментария
     */
    void deleteByAdIdAndId(Integer adId, Integer commentId);

    /**
     * Проверяет существование комментария по ID объявления и ID комментария.
     *
     * @param adId ID объявления
     * @param commentId ID комментария
     * @return true, если комментарий существует
     */
    boolean existsByAdIdAndId(Integer adId, Integer commentId);
}
