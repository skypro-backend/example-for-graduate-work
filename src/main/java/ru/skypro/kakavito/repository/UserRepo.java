package ru.skypro.kakavito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.kakavito.model.User;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    User findByFirstName(String username);

    Optional<User> findById(Long id);

    User findUserById(Integer id);
}
