package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;


import java.util.Arrays;
import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Integer> {
    Comment getByCommentId(Integer commentId);


//    Comment findByAdPkAndCommentId(Integer idAd, Integer commentId);

}
