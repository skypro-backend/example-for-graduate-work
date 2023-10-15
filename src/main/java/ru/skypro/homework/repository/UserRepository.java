package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.skypro.homework.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * поиск пользователя по логину
     */

    User findByUsername(String username);

    /**
     * Поиск пользователя по его email
     */

    User findByUserEmail(String email);

    /**
     * Проверка пользователя по email
     * @param email email пользователя
     * @return true or false
     */
    boolean existsByEmail(String email);
    @Modifying
    @Query("UPDATE User u SET " +
            "u.password = :newPassword " +
            "WHERE u.id = :id")
    User updatePassword(
            @Param("id") Integer id,
            @Param("newPassword") String newPassword);

    @Modifying
    @Query("UPDATE User SET " +
            "firstName = :first_name, " +
            "lastName = :last_name," +
            "phone = :phone," +
            "email = :email," +
            "image = :user_image" +
            " WHERE id = :id")
    User updateUser(
            @Param("first_name") String firstName,
            @Param("last_name") String lastName,
            @Param("phone") String phone,
            @Param("email") String email,
            @Param("user_image") String image,
            @Param("id") Integer id);



}
