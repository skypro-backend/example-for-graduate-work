package ru.skypro.homework.repository;

import ru.skypro.homework.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findAllByAdId(int id);
}