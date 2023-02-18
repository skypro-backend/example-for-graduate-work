package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.CommentEntity;

/**
 * Репозиторий для комментариев
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    CommentEntity findByIdAndAd_Id(Integer commentId, Integer adId);

}
