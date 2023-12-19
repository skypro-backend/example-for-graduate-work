package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    List<Comment> getCommentsByAdId(int id);

    @Query(value = "SELECT MIN(comment_id) FROM comment WHERE ad_id = :AdId",nativeQuery = true)
    Integer findFirstCommentId(int AdId);
}

