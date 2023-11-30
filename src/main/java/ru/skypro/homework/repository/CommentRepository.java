package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    //void deleteByPkAndAdEntity_Pk(Integer commentId, Long adId);
    void deleteByPk(Integer commentId);
}