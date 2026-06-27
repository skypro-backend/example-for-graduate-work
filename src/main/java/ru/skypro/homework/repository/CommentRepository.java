package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.CommentEntity;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Репозиторий для взаимодействия с сущностями комментариев {@link CommentEntity} в БД.
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    /**
     * Находит список комментариев, относящихся к определенному объявлению.
     *
     * @param adId уникальный идентификатор объявления
     * @return список найденных сущностей комментариев
     */
    List<CommentEntity> findAllByAdId(Integer adId);
}