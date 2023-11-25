package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Register;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Integer> {
}
