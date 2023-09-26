package ru.skypro.homework.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.store.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
