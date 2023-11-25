package ru.skypro.homework.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity,Long > {
}
