package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
     * @param userName логин
     * @return объект userEntity
     */
    UserEntity findUserEntityByUserName(String userName);

    /**
     * Поиск пользователя по логину и паролю.
     * @param userName логин
     * @param password пароль
     * @return объект userEntity
     */
    UserEntity findUserEntityByUserNameAndPassword(String userName, String password);

    /**
     * Метод для очистки всех данных таблицы пользователей.
     */
    @Query(value = "delete from users where id notnull;", nativeQuery = true)
    void cleanAll();
}
