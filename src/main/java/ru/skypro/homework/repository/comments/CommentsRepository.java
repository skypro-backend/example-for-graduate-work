package ru.skypro.homework.repository.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.comments.Comment;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Integer> {
}
