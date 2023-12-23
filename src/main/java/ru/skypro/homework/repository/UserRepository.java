package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);

    @Override
    Optional<User> findById(Integer id);
}
