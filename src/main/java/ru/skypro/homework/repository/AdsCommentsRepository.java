package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.models.entity.Ads;
import ru.skypro.homework.models.entity.Comments;

import java.util.List;

@Repository
public interface AdsCommentsRepository extends JpaRepository<Comments, Integer> {
    List<Comments> findCommentsByAds(Ads ads);

    Comments findCommentsByPk(Integer id);

}
