package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.UserEntity;

import java.util.Optional;

/**
 * Интерфейс для обращения к БД пользователя
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    /**
     * Метод, который ищет пользователя по имени
     * @param email - имя пользователя
     * @return объект пользователя
     */
    //Optional<UserEntity> findByUsername(String email);
    UserEntity findByUsername(String email);
}
