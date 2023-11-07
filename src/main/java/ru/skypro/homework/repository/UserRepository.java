package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
}
