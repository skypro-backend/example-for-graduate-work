package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.UserEntity;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностями пользователей ({@link UserEntity}).
 * <p>
 * Предоставляет стандартные CRUD-операции через наследование от {@link JpaRepository}.
 * Дополнительно объявлены кастомные методы для:
 * <ul>
 *   <li>Поиска пользователя по email ({@code findByEmail})</li>
 *   <li>Поиска пользователя по телефону ({@code findByPhone})</li>
 * </ul>
 *
 * @see UserEntity — JPA-сущность пользователя
 * @see JpaRepository — базовый интерфейс Spring Data JPA для управления сущностями
 * @see AdRepository — репозиторий объявлений
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    /**
     * Находит пользователя по его email.
     * <p>
     * Используется в:
     * <ul>
     *   <li>{@link ru.skypro.homework.service.impl.UserDetailsServiceImpl#loadUserByUsername(String)} — аутентификация</li>
     *   <li>{@link ru.skypro.homework.service.impl.AuthServiceImpl#register(ru.skypro.homework.dto.Register)} — проверка уникальности при регистрации</li>
     *   <li>Получении профиля пользователя</li>
     * </ul>
     *
     * @param email email пользователя (уникальное поле)
     * @return {@link Optional} с сущностью {@link UserEntity}, если найден; иначе — пустой Optional
     */
    Optional<UserEntity> findByEmail(String email);

    /**
     * Находит пользователя по его телефону.
     * <p>
     * Используется для проверки уникальности номера при регистрации или обновлении профиля.
     *
     * @param phone телефон пользователя (уникальное поле)
     * @return {@link Optional} с сущностью {@link UserEntity}, если найден; иначе — пустой Optional
     */
    Optional<UserEntity> findByPhone(String phone);
}