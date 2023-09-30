package ru.skypro.homework.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.dto.UpdateUser;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

public interface UserService {

    /** Регистрация пользователя
        * @param register       {@link Register}
        * @param encodePassword объект {@link String}  */
    void registerNewUser(Register register, String encodePassword);

    /** Обновление пароля пользователя
        * @param user {@link User} */
    void updatePassword(User user);

    /** Получение DTO пользователя
        * @param userLogin логин пользователя
        * @return объект {@link Optional<User>}    */
    Optional<User> getUserByDtoByLogin(String userLogin);

    /** Обновление информации о пользователе
        * @param Login  логин пользователя
        * @param updateUser {@link UpdateUser}
        * @return объект {@link Optional<UpdateUser>}  */
    Optional<UpdateUser> updateUserByUpdateUser(String Login,UpdateUser updateUser);


    /**
     * Получение пользователя по логину
     *
     * @return объект {@link Optional<User>}
     */
    Optional<User> getUserByLogin();

    /**
     * Изменение картинки пользователя
     *
     * @param multipartFile {@link MultipartFile}
     * @return объект {@link Optional<User>}
     */
    Optional<User> changeImage(MultipartFile multipartFile);

    /** Получение всех пользователей из БД
        * @return объект {@link Collection<UserDetails>}   */
    Collection<UserDetails> getAllUserDetails();

    void setPassword(String currentPassword, String newPassword);

    UpdateUser updateUserByUpdateUser(UpdateUser updateUser);

    @Transactional
    User getUser();

    @Transactional
    UpdateUser updateUser(UpdateUser updateUser);

    @Transactional
    void updateUserImage(MultipartFile file);
}