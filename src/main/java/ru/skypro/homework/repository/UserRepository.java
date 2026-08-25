package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.UserEntity;

import java.util.Optional;

/**
 * Репозиторий для работы с пользователями.
 * Предоставляет методы для доступа к данным пользователей в базе данных.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    /**
     * Находит пользователя по его имени.
     *
     * @param username имя пользователя
     * @return Optional с найденным пользователем или пустой Optional
     */
    Optional<UserEntity> findByUsername(String username);

    /**
     * Проверяет существование пользователя с указанным именем.
     *
     * @param username имя пользователя
     * @return true, если пользователь существует
     */
    boolean existsByUsername(String username);
}