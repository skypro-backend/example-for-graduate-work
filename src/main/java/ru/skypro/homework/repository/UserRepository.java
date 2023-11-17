package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.UserEntity;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    /**
     * Поиск пользователя по паролю.
     * @param oldPass искомый пароль
     * @return объект userEntity
     */
    UserEntity findUserEntityByPassword(String oldPass);

    /**
     * Пиоск пользователя по логину
     * @param username логин
     * @return объект userEntity
     */
    UserEntity findUserEntityByUsername(String username);

    /**
     * Поиск пользователя по логину и паролю.
     * @param userName логин
     * @param password пароль
     * @return объект userEntity
     */
    UserEntity findUserEntityByUsernameAndPassword(String userName, String password);
}
