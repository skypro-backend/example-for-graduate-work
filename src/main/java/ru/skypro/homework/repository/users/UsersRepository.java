package ru.skypro.homework.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.users.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
}
