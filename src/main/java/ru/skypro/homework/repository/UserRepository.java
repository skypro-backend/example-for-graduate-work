package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.UserEntity;


/**
 * Интерфейс для обращения к БД пользователя
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    /**
     * Метод, который ищет пользователя по email
     * @param email - логин пользователя
     * @return объект пользователя
     */
    UserEntity findByUsername(String email);
}
