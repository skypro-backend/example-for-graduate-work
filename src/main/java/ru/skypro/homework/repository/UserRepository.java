package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.UserEntity;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностью {@link UserEntity}.
 * Предоставляет методы для доступа к данным пользователей в базе данных.
 * Основные функции:
 * - стандартные CRUD-методы (наследуются от JpaRepository);
 * - поиск пользователя по email.
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    /**
     * Найти пользователя по email.
     * @param email email пользователя
     * @return Optional с найденным пользователем или пустой Optional
     */
    Optional<UserEntity> findByEmail(String email);
}
