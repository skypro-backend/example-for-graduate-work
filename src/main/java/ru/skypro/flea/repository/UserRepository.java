package ru.skypro.flea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.flea.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
