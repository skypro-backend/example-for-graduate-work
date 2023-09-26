package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
