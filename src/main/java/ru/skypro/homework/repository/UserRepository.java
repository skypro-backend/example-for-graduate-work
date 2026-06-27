package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.UserEntity;

import java.util.Optional;

/**
 * Репозиторий для взаимодействия с сущностями пользователей {@link UserEntity} в БД.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    /**
     * Ищет пользователя по его адресу электронной почты (username).
     *
     * @param email электронная почта пользователя (логин)
     * @return {@link Optional}, содержащий найденного пользователя, либо пустой
     */
    Optional<UserEntity> findByEmail(String email);
}