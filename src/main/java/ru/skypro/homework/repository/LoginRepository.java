package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {
}
