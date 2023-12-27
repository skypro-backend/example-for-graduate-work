package ru.skypro.homework.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.skypro.homework.entity.CommentEntity;

import java.util.List;

public interface CommentEntityRepository extends JpaRepository<CommentEntity,Long > {
    List<CommentEntity> findByAdId(Integer id);
    void deleteByIdAndAdId_id(Integer commentId, Integer adId);

    List<CommentEntity> findByAdId_id(Integer adId);

//    @Query("DELETE FROM e WHERE e.ad_id = :adId")
//    void deleteByAdId(@Param("adId") Integer adId);

}