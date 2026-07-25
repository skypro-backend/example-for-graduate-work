package com.skypro.repository;

import com.skypro.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностью {@link UserEntity}.
 * <p>
 * Предоставляет стандартные CRUD-методы, унаследованные от {@link JpaRepository},
 * а также дополнительные методы для поиска пользователей по имени.
 * </p>
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    /**
     * Находит пользователя по имени (логину).
     *
     * @param username имя пользователя (уникальное поле)
     * @return {@link Optional}, содержащий найденного пользователя, или пустой {@link Optional}, если пользователь не найден
     */
    Optional<UserEntity> findByUsername(String username);
}
