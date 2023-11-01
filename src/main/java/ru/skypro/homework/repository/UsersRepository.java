package ru.skypro.homework.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Users;

import java.util.Optional;
/**
 * The repository for getting methods to work with user's database
 * @author Sulaeva Marina
 */
@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);





}
