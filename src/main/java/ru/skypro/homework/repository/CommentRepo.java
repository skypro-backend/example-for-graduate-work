package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.model.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {
    CommentsDTO getAllByAdId(Long pk);

    Optional<Comment> getCommentByText(String text);
}
