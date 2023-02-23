package ru.skypro.homework.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {


  Optional<UserEntity> findByEmail(String email);


}
