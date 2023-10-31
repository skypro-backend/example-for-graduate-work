package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.UserAvatar;

@Repository
public interface UserAvatarRepository extends JpaRepository<UserAvatar, Long> {

}
