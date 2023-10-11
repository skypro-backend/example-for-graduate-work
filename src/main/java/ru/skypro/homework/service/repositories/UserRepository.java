package ru.skypro.homework.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.service.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    // Создаем метод findByUsername
    // для поиска пользователя по имени пользователя
    UserEntity findByUsername(String username);


    UserEntity findByEmail(String userEmail);
}
