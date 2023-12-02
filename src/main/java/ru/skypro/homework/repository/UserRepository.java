package ru.skypro.homework.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.homework.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
    boolean existsUserByUsername (String username);
}
