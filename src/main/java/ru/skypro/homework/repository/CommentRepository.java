package ru.skypro.homework.repository;

import ru.skypro.homework.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findAllByAdId(Long id);

    Optional<CommentEntity> findById(Long commentId);

    void deleteById(Long commentId);
}