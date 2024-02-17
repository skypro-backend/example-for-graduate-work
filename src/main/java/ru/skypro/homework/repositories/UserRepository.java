package ru.skypro.homework.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}