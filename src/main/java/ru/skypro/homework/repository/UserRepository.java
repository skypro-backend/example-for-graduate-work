package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Integer>, JpaSpecificationExecutor <User> {
      Optional <User> findByEmail(String email);

      boolean existsByEmailContains(String email);
}
