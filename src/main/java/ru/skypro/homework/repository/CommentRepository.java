package ru.skypro.homework.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Optional<Comment> findByPk(Integer pk);

    Optional<List<Comment>> findCommentsByAd_Pk(Integer pk);

    List<Comment> deleteAllByAd_Pk(Integer pk);
}
