package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.NewPassword;

import java.util.Optional;

@Repository
public interface NewPasswordRepository extends JpaRepository<NewPassword, Integer> {
    Optional<NewPassword> findById(Integer id);
}
