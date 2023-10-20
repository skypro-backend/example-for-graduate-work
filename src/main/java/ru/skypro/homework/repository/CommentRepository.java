package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.Comments;
import ru.skypro.homework.entity.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {

//    @Query(value = "SELECT * FROM comments",
//            nativeQuery = true)
//    List<Comment> findAllComments();
//
//    @Query(value = "SELECT * FROM comments " +
//            "WHERE author_id= :author",
//            nativeQuery = true)
//    List<Comment> findAllCommentByAuthor(Integer author);
//
//    @Query(value = "SELECT * FROM comments " +
//            "WHERE comment_id= :pk",
//            nativeQuery = true)
//    Comment findCommentByCommentId(Integer pk);
//
//    @Query("SELECT new ru.skypro.homework.dto.comment" +
//            ".Comments((SELECT COUNT(c.pk) FROM Comment c), " +
//            "(SELECT * FROM Comment c WHERE c.ad.adId = :adId))")
//    Comments findCommentsByAdId(Integer adId);
}
