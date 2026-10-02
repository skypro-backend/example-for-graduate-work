package ru.skypro.homework.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.db.model.UserModel;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByEmail(String email);

    boolean existsByEmail(String email);
}
