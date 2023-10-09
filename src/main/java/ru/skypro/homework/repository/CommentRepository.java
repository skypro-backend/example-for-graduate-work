package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByAd_Pk(Integer id);
    @Transactional
    void deleteCommentByPkAndAd_Pk(Integer commentId,Integer adId);
    Optional<Comment> findByPkAndAd_Pk(Integer commentId, Integer adId);

}
