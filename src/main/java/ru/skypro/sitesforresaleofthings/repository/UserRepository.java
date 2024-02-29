package ru.skypro.sitesforresaleofthings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.sitesforresaleofthings.entity.User;

/**
 * Создаем репозиторий для сущности "Пользователь"
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}