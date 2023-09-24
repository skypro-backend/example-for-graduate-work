package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.Authorities;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {
}
