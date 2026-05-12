package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.CommentEntity;

import java.util.List;

/**
 * Репозиторий для работы с сущностями комментариев ({@link CommentEntity}).
 * <p>
 * Предоставляет стандартные CRUD-операции через наследование от {@link JpaRepository}.
 * Дополнительно объявлен метод для:
 * <ul>
 *   <li>Поиска всех комментариев по идентификатору объявления ({@code findByAdPk})</li>
 * </ul>
 *
 * @see CommentEntity — JPA-сущность комментария
 * @see JpaRepository — базовый интерфейс Spring Data JPA для управления сущностями
 * @see AdRepository — репозиторий объявлений
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    /**
     * Находит все комментарии, оставленные к объявлению с указанным идентификатором.
     * <p>
     * Используется в:
     * <ul>
     *   <li>{@link ru.skypro.homework.service.impl.CommentServiceImpl#getComments(Integer)} — получение списка комментариев</li>
     *   <li>Проверке прав при редактировании/удалении</li>
     * </ul>
     * Комментарии возвращаются в порядке их добавления (по умолчанию — по ID).
     *
     * @param adPk идентификатор объявления
     * @return список сущностей {@link CommentEntity}, может быть пустым
     */
    List<CommentEntity> findByAdPk(Integer adPk);
}