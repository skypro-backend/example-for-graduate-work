package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.Comment;

import java.util.List;

/**
 * <h2>CommentRepository</h2>
 * Provides CRUD operations with comments stored in database
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByCreatedAt(int id);
}
