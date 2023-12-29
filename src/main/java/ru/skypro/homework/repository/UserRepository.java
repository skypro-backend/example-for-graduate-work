package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    /**
     * <h2>Find user by email in database. Application considers email as user name for login </h2>
     *
     * @param email arg to search user entity
     * @return Optional
     */
    Optional<User> findByEmail(String email);
}
