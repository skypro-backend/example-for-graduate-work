package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.AdEntity;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для работы с сущностями объявлений ({@link AdEntity}).
 * <p>
 * Предоставляет стандартные CRUD-операции через наследование от {@link JpaRepository}.
 * Дополнительно объявлены кастомные методы для:
 * <ul>
 *   <li>Поиска объявлений по ID автора ({@code findByAuthorId})</li>
 *   <li>Поиска объявления по заголовку ({@code findByTitle})</li>
 * </ul>
 *
 * @see AdEntity — JPA-сущность объявления
 * @see JpaRepository — базовый интерфейс Spring Data JPA для управления сущностями
 * @see UserRepository — аналогичный репозиторий для пользователей
 */
@Repository
public interface AdRepository extends JpaRepository<AdEntity, Integer> {

    /**
     * Находит все объявления, созданные пользователем с указанным ID.
     * <p>
     * Используется в:
     * <ul>
     *   <li>{@link ru.skypro.homework.service.impl.AdServiceImpl#getAdsByUser(String)} — получение "моих объявлений"</li>
     *   <li>Проверке прав при редактировании/удалении</li>
     * </ul>
     *
     * @param authorId ID пользователя — автора объявлений
     * @return список сущностей {@link AdEntity}, может быть пустым
     */
    List<AdEntity> findByAuthorId(Integer authorId);

    /**
     * Находит объявление по точному совпадению заголовка.
     * <p>
     * Возвращает первый найденный результат (если таких несколько).
     *
     * @param title точное название заголовка объявления
     * @return {@link Optional} с сущностью {@link AdEntity}, если найдено; иначе — пустой Optional
     */
    Optional<AdEntity> findByTitle(String title);
}