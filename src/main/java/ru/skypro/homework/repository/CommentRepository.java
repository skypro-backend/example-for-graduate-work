package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.CommentEntity;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByAdId(long id);
    void deleteByIdAndAdId(long adId, long commentId);
    void deleteAllByAdId(long adId);

    CommentEntity findByIdAndAd_Id(long adId, long commentId);
}
