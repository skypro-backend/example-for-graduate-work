package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.PasswordIsNotMatchException;
import ru.skypro.homework.exception.UserNotFound;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.logger.FormLogInfo;
import ru.skypro.homework.service.UserService;

/**
 * Сервис пользователей
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encoder = encoder;
    }

    /**
     * Получить данные пользователя
     */
    @Override
    @Transactional
    public User getUser(Authentication authentication) {
        log.info("Получить данные пользователя" + FormLogInfo.getInfo());
        String nameEmail = authentication.getName();
        UserEntity userEntity = findEntityByEmail(nameEmail);
        return userMapper.toDtoUser(userEntity);
    }

    /**
     * Обновить данные пользователя
     */
    @Override
    @Transactional
    public UpdateUser updateUser(UpdateUser newpUpdateUserDto, Authentication authentication) {
        log.info("Обновить данные пользователя:  " + FormLogInfo.getInfo());
        String nameEmail = authentication.getName();
        UserEntity userEntity = findEntityByEmail(nameEmail);
        long id = userEntity.getId();

        UserEntity oldUser = findById(id);

        oldUser.setFirstName(newpUpdateUserDto.getFirstName());
        oldUser.setLastName(newpUpdateUserDto.getLastName());
        oldUser.setPhone(newpUpdateUserDto.getPhone());

        userRepository.save(oldUser);
        log.info("Пользователь успешно обновлен в БД:  "+FormLogInfo.getInfo());

        return userMapper.toDtoUpdateUser(oldUser);
    }

    /**
     * Установить пароль пользователя
     */
    @Override
    @Transactional
    public void setPassword(NewPassword newPass, Authentication authentication) {
        log.info("вызов setPassword :" + FormLogInfo.getInfo());
        String oldPassword = newPass.getCurrentPassword();
        String encodeNewPassword = encoder.encode(newPass.getNewPassword());
        UserEntity userEntity = findEntityByEmail(authentication.getName());
        //проверяем совпадают ли старый пароль, введенный пользователем, и пароль сохраненный в БД
        if (!encoder.matches(oldPassword, userEntity.getPassword()))
            throw new PasswordIsNotMatchException();
        else
            userEntity.setPassword(encodeNewPassword);
        userRepository.save(userEntity);
//        return userMapper.toDtoNewPassword(userEntity);
    }

    /**
     * найти пользователя по email - логину
     *
     * @param email email - логину пользователя
     * @return пользователь
     */
    @Override
    public UserEntity findEntityByEmail(String email) {
        log.info("Пользователь найден по email: " + FormLogInfo.getInfo());
//        return userRepository.findByEmail(email).get();
        return userRepository.findByEmail(email).orElseThrow(UserNotFound::new);
    }

    /**
     * найти пользователя по id
     *
     * @param id id пользователя
     * @return пользователь
     */
    public UserEntity findById(long id) {
        log.info("Пользователь найден по Id: " + FormLogInfo.getInfo());
        return userRepository.findById(id).orElseThrow(UserNotFound::new);
    }
}