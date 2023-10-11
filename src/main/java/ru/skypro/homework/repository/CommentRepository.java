package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ru.skypro.homework.pojo.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPk(Long pk);

    @Query("SELECT c FROM Comment c WHERE c.pk = :pk AND c.commentId = :commentId")
    Comment findByPkAndCommentId(Long pk, Long commentId);
}
