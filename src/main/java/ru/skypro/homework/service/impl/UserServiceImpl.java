package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
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

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**
     * Получить данные пользователя
     */
    @Override
    public User getUser(Authentication authentication) {
        log.info(FormLogInfo.getInfo());
        String nameEmail = authentication.getName();
        UserEntity userEntity = findEntityByEmail(nameEmail);
        return userMapper.toDtoUser(userEntity);
    }

    /**
     * Обновить данные пользователя
     */
    @Override
    public UpdateUser updateUser(UpdateUser newpUpdateUserDto, Authentication authentication) {
        String nameEmail = authentication.getName();
        UserEntity userEntity = findEntityByEmail(nameEmail);
        long id = userEntity.getId();

        UserEntity oldUser = findById(id);

        oldUser.setFirstName(newpUpdateUserDto.getFirstName());
        oldUser.setLastName(newpUpdateUserDto.getLastName());
        oldUser.setPhone(newpUpdateUserDto.getPhone());

        userRepository.save(oldUser);
        log.info(FormLogInfo.getInfo());

        return userMapper.toDtoUpdateUser(oldUser);
    }

    /**
     * Установить пароль пользователя
     */
    @Override
    public NewPassword setPassword(NewPassword newPassword) {
        log.info(FormLogInfo.getInfo());
        return null;
    }

    /**
     * найти пользователя по email - логину
     *
     * @param email email - логину пользователя
     * @return пользователь
     */
    private UserEntity findEntityByEmail(String email) {
        log.info(FormLogInfo.getInfo());
//        return userRepository.findByEmail(email).get();
        return userRepository.findByEmail(email).orElseThrow(UserNotFound::new);
    }

    /**
     * найти пользователя по id
     *
     * @param id id пользователя
     * @return пользователь
     */
    private UserEntity findById(long id) {
        log.info("Пользователь найден: " + FormLogInfo.getInfo());
        return userRepository.findById(id).orElseThrow(UserNotFound::new);
    }
}