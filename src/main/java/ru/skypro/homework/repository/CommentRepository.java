package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.CommentModel;

@Repository
public interface CommentRepository extends JpaRepository<CommentModel, Integer> {
    void deleteCommentModelByIdAndAds_Id( Integer id, Integer adsId );
}
