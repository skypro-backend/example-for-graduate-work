package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий комментариев.
 */
public interface CommentRepository
        extends JpaRepository<CommentEntity, Long> {

    /**
     * Возвращает комментарии объявления
     * в порядке их создания.
     *
     * @param ad объявление
     * @return список комментариев
     */
    List<CommentEntity> findAllByAdOrderByCreatedAtAsc(
            AdEntity ad
    );

    /**
     * Находит комментарий внутри конкретного объявления.
     *
     * @param id идентификатор комментария
     * @param ad объявление
     * @return найденный комментарий
     */
    Optional<CommentEntity> findByIdAndAd(
            Long id,
            AdEntity ad
    );
}