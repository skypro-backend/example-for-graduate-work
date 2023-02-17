package ru.skypro.homework.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.CommentEntity;

/**
 * Репозиторий для комментариев
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    CommentEntity findByIdAndAd_Id(Integer commentId, Integer adId);

    /**
     * Поиск всех комментариев объявления по ads_id
     * @param adId
     * @return
     */
    Collection<CommentEntity> getCommentEntitiesByAuthor (Integer adId);

    /**
     * Удаление комментария по author_id
     */
    void deleteByAuthor_Id(Integer authorId);

}
