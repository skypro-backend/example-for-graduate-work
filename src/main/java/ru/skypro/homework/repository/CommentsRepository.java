package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Comment;



@Repository
public interface CommentsRepository extends JpaRepository<Comment, Integer> {


    List<Comment> findByAd(Ad ad);

    Comment findByAdPkAndCommentId(Integer idAd, Integer commentId);
}
