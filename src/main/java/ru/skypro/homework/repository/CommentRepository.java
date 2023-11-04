package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Comment;

import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
    List<Comment> findAllByAdId(Integer advertId);

    void deleteAllByPk(Integer id);

    List<Comment> findAllByAdPk(Integer id);

    void deleteAllByAdPk(Integer id);
}

