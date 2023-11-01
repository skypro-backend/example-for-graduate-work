package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.CommentEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findCommentEntitiesByAdEntity_Id(Integer id);
    Optional<CommentEntity> findByIdAndAdEntity_Id(int commentId, int adId);
    void deleteAllByAdEntity_Id(int adId);

}
