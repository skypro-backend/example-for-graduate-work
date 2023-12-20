package ru.skypro.homework.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity,Long > {
    Optional<UserEntity> findByPassword(String currentPassword);
    Optional<UserEntity> findByEmail(String email);
    Optional <UserEntity> findByImageEntity_filePath(String filePath);
    Optional <UserEntity> findById(Integer id);
}
