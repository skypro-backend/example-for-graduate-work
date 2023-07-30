package ru.skypro.flea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.flea.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
