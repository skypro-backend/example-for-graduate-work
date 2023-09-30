package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.homework.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("")
    List<Comment> getAllCommentsByAdId(Integer id);
    Comment deleteCommentByPkAndAd_Pk(Integer commentId,Integer adId);
}
