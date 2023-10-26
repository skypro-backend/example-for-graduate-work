package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.AdsUserDetails;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.projections.NewPassword;
import ru.skypro.homework.projections.Register;
import ru.skypro.homework.projections.UpdateUser;
import ru.skypro.homework.repository.UserRepo;
import ru.skypro.homework.service.UserService;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

//@Service
//@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDetailsManager userDetailsManager;
    private final AdsUserDetails adsUserDetails;
    private final PasswordEncoder encoder;
    private final UserRepo userRepo;


    /**
     * Получение информации о пользователе из репозитория
     */

    public UserModel find(String username) {
        return userRepo.findByUserName(username).orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Получиение информации о пользователе из автооризации
     */
    @Override
    public UserModel find() {
        var username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        return find(username);
    }

    /**
     * Создание пользователя
     */
    @Override
    public void createUser(Register register) {
        var user = find(register.getUsername());
        userRepo.save(user);
    }

    /**
     * Чтение информации о пользователе
     */
    @Override
    public UserDTO getUser() {
        find();
        return UserMapper.mapToUserDTO(
                Objects.requireNonNull(userRepo
                        .findByUserName(adsUserDetails.getUser()
                                .getUserName()).orElse(null)));
    }

    /**
     * Редактирование пароля
     */
    @Override
    public void updatePassword(NewPassword newPassword) {
        var password1 = newPassword.getCurrentPassword();
        var newPassword1 = encoder.encode(newPassword.getNewPassword());
        userDetailsManager.changePassword(password1, newPassword1);
    }

    /**
     * Обновление информации о пользователе
     */
    @Override
    public void updateUser(UpdateUser updateUserDto) {
        var user = find();
        UserMapper.mapToUserDTO(user);
    }

    /**
     * Обновление аватара  пользователя
     */
    @Override
    public void update(MultipartFile image) {

    }
}
