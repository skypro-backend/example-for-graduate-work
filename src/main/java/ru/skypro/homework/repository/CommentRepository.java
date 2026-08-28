package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.CommentEntity;

import java.util.List;

/**
 * Репозиторий для работы с сущностью {@link CommentEntity}.
 * Предоставляет методы для доступа к данным комментариев в базе данных.
 * Основные функции:
 * - стандартные CRUD-методы (наследуются от JpaRepository);
 * - поиск комментариев по ID объявления.
 */
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    /**
     * Найти все комментарии по ID объявления.
     * @param adId ID объявления
     * @return список комментариев, привязанных к указанному объявлению
     */
    List<CommentEntity> findByAdId(Integer adId);
}
