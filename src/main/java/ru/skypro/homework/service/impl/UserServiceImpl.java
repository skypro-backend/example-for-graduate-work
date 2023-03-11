package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import ru.skypro.homework.service.AvatarService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository usersRepository;
    private final UserMapper userMapper;
    private final AvatarService avatarService;

    public NewPassword setPassword(NewPassword password) {
        return null;
    }

    public User getUsers(String username) {
        return userMapper.userEntityToDto(getUserByUserName(username));
    }

    public UserEntity getUserByUserName(String username) {
        return usersRepository.findUserEntityByUsername(username).orElseThrow(() -> {
            log.error("Не найден пользователь: {}", username);
            return new UserNotFoundException(username);
        });
    }

    public User updateUser(String username, User user) {
        UserEntity userEntity = userMapper.userDtoToEntity(user);
        UserEntity newUser = getUserByUserName(username);
        if (userEntity.getEmail() != null) {
            newUser.setEmail(userEntity.getEmail());
        }
        if (userEntity.getPhone() != null) {
            newUser.setPhone(userEntity.getPhone());
        }
        if (userEntity.getFirstName() != null) {
            newUser.setFirstName(userEntity.getFirstName());
        }
        if (userEntity.getLastName() != null) {
            newUser.setLastName(userEntity.getLastName());
        }
        newUser = usersRepository.save(newUser);
        log.info("Пользователь обновлен (id: {})", newUser.getId());
        return userMapper.userEntityToDto(newUser);
    }

    public ResponseEntity<Void> updateUserImage(String username, MultipartFile image) throws IOException {
        UserEntity user = getUserByUserName(username);
        updateImageOfUser(user, image);
        usersRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void updateImageOfUser(UserEntity user, MultipartFile image) throws IOException {
        if (user.getAvatar() == null) {
            //user.setAvatar(avatarService.);
            log.info("Добавляем аватар пользователю (id: {})", user.getId());
        } else {
            //user.setAvatar(avatarService.);
            log.info("Обновили аватар (id: {}) пользователю (id: {})", user.getAvatar().getId(), user.getId());
        }
    }
}
