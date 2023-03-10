package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.CommentEntity;


import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity,Integer> {
    Optional<CommentEntity> findCommentEntitiesById(Integer id);
}
