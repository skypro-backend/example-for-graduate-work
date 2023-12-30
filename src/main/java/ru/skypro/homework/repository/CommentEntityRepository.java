package ru.skypro.homework.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.skypro.homework.entity.CommentEntity;

import java.util.List;
import java.util.Optional;

public interface CommentEntityRepository extends JpaRepository<CommentEntity,Long > {
    void deleteById(Integer commentId);
    Optional<CommentEntity> findById(Integer commentId);

    List<CommentEntity> findByAdEntity_id(Integer adId);

//    @Query("DELETE FROM e WHERE e.ad_id = :adId")
//    void deleteByAdId(@Param("adId") Integer adId);

}