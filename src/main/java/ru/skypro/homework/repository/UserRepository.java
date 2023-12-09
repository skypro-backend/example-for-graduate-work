package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    boolean existsUserByUsername(String username);
}
