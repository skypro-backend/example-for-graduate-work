package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository <Comment, Integer>, JpaSpecificationExecutor <Comment> {
      Optional<Comment> findByIdAndAdId(Integer id, Integer adId);
      Collection<Comment> findAllByAdId(Integer adId);


}
