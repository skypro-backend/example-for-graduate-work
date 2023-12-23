package ru.skypro.homework.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.CommentEntity;

import java.util.List;

public interface CommentEntityRepository extends JpaRepository<CommentEntity,Long > {
    void deleteById(Integer commentId);
    List<CommentEntity> findByAdId_Id(Integer id);
    void deleteByIdAndAdId_id(Integer commentId, Integer adId);
}