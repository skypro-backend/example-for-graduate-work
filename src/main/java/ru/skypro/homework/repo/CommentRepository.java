package ru.skypro.homework.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.CommentEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findAllByAdId(Integer adId);
    void deleteByAdIdAndId(Integer adId, Integer Id);
    CommentEntity findByAdIdAndId(Integer adId, Integer Id);
}

