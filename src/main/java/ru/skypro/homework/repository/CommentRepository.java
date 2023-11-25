package ru.skypro.homework.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    List<Comment> findAllByAd_pk(int id);
    Comment findByPk(int id);
}
