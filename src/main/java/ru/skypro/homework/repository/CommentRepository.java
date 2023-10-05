package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.projection.Comments;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("SELECT c.pk, c.createdAt, c.text, c.user, c.ad FROM Comment c WHERE c.ad.pk = :id")
    List<Comments> getAllCommentsByAdId(@Param("id") Integer id);
    Comment deleteCommentByPkAndAd_Pk(Integer commentId,Integer adId);
    Comment findByPkAndAd_Pk(Integer commentId,Integer adId);

}
