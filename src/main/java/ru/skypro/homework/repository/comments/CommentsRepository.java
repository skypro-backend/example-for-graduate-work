package ru.skypro.homework.repository.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.ads.Ad;
import ru.skypro.homework.entity.comments.Comment;

import java.util.Optional;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Integer> {

    Optional<Comment> findByAd_PkAndPk(Integer adId, Integer commentId);

}
