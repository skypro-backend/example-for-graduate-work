package ru.skypro.homework.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "select * from user_ order by id", nativeQuery = true)
    List<User> findAllUsers();

    Optional<User> findUserByEmail(String email);
    Optional<User> getUserByEmailIgnoreCase(String username);
}
