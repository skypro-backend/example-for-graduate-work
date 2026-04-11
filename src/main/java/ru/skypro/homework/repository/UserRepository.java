package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.UserEntity;

import java.util.Optional;
/**
 * Интерфейс для работы с пользователями в базе данных.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    /**
     * Находит пользователя по email.
     *
     * @param email email пользователя
     * @return найденный пользователь, если существует
     */
    Optional<UserEntity> findByEmail(String email);

}
