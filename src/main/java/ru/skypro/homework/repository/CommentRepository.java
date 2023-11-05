package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.CommentEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findAll();
    CommentEntity findByAdRelatedAndId(Ad adRelated, int id);
    List<CommentEntity> findAllByAdRelated(Ad adRelated);
    void deleteAllInBatch();
    void deleteById(int id);

}
