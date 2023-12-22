package ru.skypro.homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Comment;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
