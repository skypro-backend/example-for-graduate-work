package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAll();
    Comment findByAdRelatedAndId(AdEntity adRelated, int id);
    List<Comment> findAllByAdRelated(AdEntity adRelated);
    void deleteAllInBatch();
    @Modifying
    @Query("delete from Comment b where b.id=:id")
    void deleteById(int id);

}
