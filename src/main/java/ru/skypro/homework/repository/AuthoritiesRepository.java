package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.Authorities;

import java.util.Optional;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {
    Optional<Authorities> findByUsername(String username);
}
