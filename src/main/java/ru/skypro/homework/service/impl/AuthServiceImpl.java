package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.model.dto.NewPasswordDto;
import ru.skypro.homework.model.dto.RegisterReqDto;
import ru.skypro.homework.model.dto.RoleEnum;
import ru.skypro.homework.model.entity.ProfileUser;
import ru.skypro.homework.model.mapper.UserMapper;
import ru.skypro.homework.model.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    public AuthServiceImpl(UserDetailsManager manager, UserRepository userRepository) {
        this.manager = manager;
        this.userRepository = userRepository;
        this.encoder = new BCryptPasswordEncoder();
    }

    /**
     * Получить статус об авторизации пользователя
     *
     * @param userName логин пользователя (email)
     * @param password пароль пользователя
     * @return false, если пользователя не существует, либо true
     */
    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        String encryptedPassword = userDetails.getPassword();
        String encryptedPasswordWithoutEncryptionType = encryptedPassword.substring(8);
        return encoder.matches(password, encryptedPasswordWithoutEncryptionType);
    }

    /**
     * Получить статус о регистрации нового пользователя
     *
     * @param registerReqDto сущность с данными пользователя с формы регистрации
     * @param roleEnum       роль позльзователя USER / ADMIN
     * @return false, если пользователь существует в БД, true, если не существует в БД
     */
    @Override
    public boolean register(RegisterReqDto registerReqDto, RoleEnum roleEnum) {
        if (manager.userExists(registerReqDto.getUsername())) {
            return false;
        }
        manager.createUser(
                User.withDefaultPasswordEncoder()
                        .password(registerReqDto.getPassword())
                        .username(registerReqDto.getUsername())
                        .roles(roleEnum.name())
                        .build()
        );
        ProfileUser userProfile = UserMapper.INSTANCE.registerReqDtoToUser(registerReqDto);
        userRepository.save(userProfile);
        return true;
    }
    @Override
    public boolean changePassword(NewPasswordDto newPassword, String username) {
        if (login(username, newPassword.getCurrentPassword())) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            manager.changePassword(
                    newPassword.getCurrentPassword(),
                    "{bcrypt}" + encoder.encode(newPassword.getNewPassword()));
            return true;
        }
        return false;
    }
}
