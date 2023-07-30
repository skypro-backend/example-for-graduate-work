package ru.skypro.flea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.flea.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
