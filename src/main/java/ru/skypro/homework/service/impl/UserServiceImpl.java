package ru.skypro.homework.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapping.UserMapper;
import ru.skypro.homework.repository.UserRepository;

import java.io.IOException;

@Service
public class UserServiceImpl {

    private final UserRepository usersRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository usersRepository, UserMapper userMapper) {
        this.usersRepository = usersRepository;
        this.userMapper = userMapper;
    }

    /**
     * Метод установки пароля
     *
     * @param password - новый пароль
     * @return возвращает установленный пароль
     */
    public NewPassword setPassword(NewPassword password) {
        return null;
    }

    /**
     * Метод получения DTO пользователя
     *
     * @param username - логин пользователя
     * @return User - DTO
     */
    public User getUsers(String username) {
        return userMapper.userEntityToDto(getUserByUserName(username));
    }

    /**
     * Метод получения Entity пользователя
     *
     * @param username - логин пользователя
     * @return UserEntity
     */
    public UserEntity getUserByUserName(String username) {
        return usersRepository.findUserEntityByUsername(username).orElseThrow(() ->
                new UserNotFoundException(username));
    }

    /**
     * Метод изменения пользователя
     *
     * @param user - пользователь на изменение
     * @return возвращает обновленного пользователя
     */
    public User updateUser(String username, User user) {
        UserEntity newUser = userMapper.userDtoToEntity(user);
        UserEntity oldUser = getUserByUserName(username);
        if (newUser.getEmail() != null) {
            oldUser.setEmail(newUser.getEmail());
        }
        if (newUser.getPhone() != null) {
            oldUser.setPhone(newUser.getPhone());
        }
        if (newUser.getFirstName() != null) {
            oldUser.setFirstName(newUser.getFirstName());
        }
        if (newUser.getLastName() != null) {
            oldUser.setLastName(newUser.getLastName());
        }
        oldUser = usersRepository.save(oldUser);
        return userMapper.userEntityToDto(oldUser);
    }

    /**
     * Метод обновления изображения пользователя
     */
    public ResponseEntity<Void> updateUserImage(String username, MultipartFile image) throws IOException {
        UserEntity user = getUserByUserName(username);
        updateImageOfUser(user, image);
        usersRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void updateImageOfUser(UserEntity user, MultipartFile image) throws IOException {
    }
}
