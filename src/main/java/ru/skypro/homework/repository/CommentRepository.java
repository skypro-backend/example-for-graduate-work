package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.Comment;

import java.util.Collection;
import java.util.Optional;

public interface CommentRepository extends JpaRepository <Comment, Integer> {
      Optional <Comment> findByIdAndAdId(Integer id, Integer pk);

      Collection <Comment> findAllByAdId(Integer pk);

}
