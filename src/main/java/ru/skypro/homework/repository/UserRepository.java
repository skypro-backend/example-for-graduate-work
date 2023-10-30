package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.skypro.homework.model.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository <User, Integer>, JpaSpecificationExecutor <User> {
      Optional <User> findByEmail(String email);
}
