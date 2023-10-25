package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.UserModel;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Integer> {
//    Optional<UserModel> findByFirstName(String firstName);
}
