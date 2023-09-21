package ru.skypro.homework.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.homework.entity.UserEntity;

public interface UsersRepository extends CrudRepository<UserEntity, Integer> {

    UserEntity findByUsername(String username);

}
