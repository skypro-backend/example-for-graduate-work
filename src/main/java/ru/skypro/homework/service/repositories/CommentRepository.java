package ru.skypro.homework.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.skypro.homework.service.entities.AdEntity;
import ru.skypro.homework.service.entities.CommentEntity;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    @Query("SELECT c FROM CommentEntity c WHERE c.id = :commentId AND c.adEntity.pk = :adId")
    Optional<CommentEntity> findCommentByCommentIdAndAdId(@Param("adId") Integer adId , @Param("commentId") Integer commentId);

    void deleteAllByAdEntity(AdEntity adEntity);

}
