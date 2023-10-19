package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.dto.Registred;

public interface RergistredRepository extends JpaRepository<Registred, Long> {
    void create(Registred register);
}
