package ru.skypro.kakavito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.kakavito.dto.CommentsDTO;
import ru.skypro.kakavito.model.Comment;

import java.util.Optional;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {
    CommentsDTO getAllByAdPk(int ad_pk);

    Optional<Comment> getCommentByText(String text);
}

