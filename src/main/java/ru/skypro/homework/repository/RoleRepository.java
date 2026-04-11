package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.RoleEntity;

import java.util.Optional;
/**
 * Интерфейс для получения ролей пользователей из базы данных.
 */

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    /**
     * Находит роль по названию.
     *
     * @param name системное имя роли
     * @return найденная роль, если существует
     */
    Optional<RoleEntity> findByName(String name);
}
