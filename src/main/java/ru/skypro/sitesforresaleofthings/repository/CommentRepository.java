package ru.skypro.sitesforresaleofthings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.sitesforresaleofthings.entity.Comment;

/**
 * Создаем репозиторий для сущности "Комментарий"
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}