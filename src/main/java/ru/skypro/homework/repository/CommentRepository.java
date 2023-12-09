package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.models.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByAd_Id(Integer adId);

    Optional<Comment> findByIdAndAd_Id(Integer id, Integer adId);

    boolean existsByIdAndUserEmail(Integer id, String email);
}
