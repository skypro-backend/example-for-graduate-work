package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.UserEntity;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Поиск пароля в таблице с пользователями
     * @param oldPass искомый пароль
     * @return пользователя с подходящим паролем
     */
    UserEntity findUserEntityByPassword(String oldPass);

    /**
     * Пиоск пользователя по логину
     * @param username логин
     * @return объект userEntity
     */
    UserEntity findUserEntityByUsername(String username);
}
