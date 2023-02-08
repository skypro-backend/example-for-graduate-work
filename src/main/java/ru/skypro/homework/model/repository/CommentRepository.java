package ru.skypro.homework.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.entity.Comment;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query(nativeQuery = true, value = "select author_id from comments where ads_id = ?1 and id = ?2")
    Integer getUserProfileId(int adsId, int commentId);

    void deleteByAdsIdAndId(Integer adsId, Integer commentId);

    Optional<Comment> getByAdsIdAndId(Integer adsId, Integer commentId);
    Collection<Comment> getByAdsId(Integer adsId);

}