package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c JOIN FETCH c.author WHERE c.ad.id = :adId")
    List<Comment> findAllByAdIdWithAuthor(@Param("adId") Long adId);

    @Query("SELECT c FROM Comment c JOIN FETCH c.author JOIN FETCH c.ad WHERE c.id = :id AND c.ad.id = :adId")
    Optional<Comment> findByIdAndAdIdWithAuthor(@Param("id") Long id, @Param("adId") Long adId);
}
