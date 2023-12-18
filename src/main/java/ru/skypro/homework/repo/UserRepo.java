package ru.skypro.homework.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    UserEntity findByLogin(String login);

}
