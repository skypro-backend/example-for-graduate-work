package ru.skypro.homework.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
 }