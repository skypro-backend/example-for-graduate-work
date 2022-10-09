package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.AdsComment;

import java.util.List;
import java.util.Optional;

public interface AdsCommentRepository extends JpaRepository<AdsComment, Integer> {
    List<AdsComment> findAdsCommentByPk(int pk);

    Optional<AdsComment> findAdsCommentByPkAndAuthor(int pk, int author);
}
