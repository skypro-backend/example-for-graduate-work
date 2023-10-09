package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.projection.UserView;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCase(String email);

    @Query("SELECT new ru.skypro.homework.projection.UserView " +
            "(id," +
            "email," +
            "firstName," +
            "lastName," +
            "phone," +
            "role," +
            "image) " +
            "FROM User " +
            "WHERE email = :mail")
    Optional<UserView> findViewByEmailIgnoreCase(@Param("mail") String email);
}