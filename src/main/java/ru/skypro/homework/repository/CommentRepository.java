package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.models.Comment;

public interface CommentRepository extends JpaRepository<Comment , Long> {
}
