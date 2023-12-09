package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByAd_pk(int id);

    Comment findByPk(int id);
    Comment findByAdPkAndPk(int AdPk, int pk);
}
