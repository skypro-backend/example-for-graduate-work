package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.exception.PasswordIsNotMatchException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.entities.UserEntity;
import ru.skypro.homework.repositories.PhotoRepository;
import ru.skypro.homework.repositories.UserRepository;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Service

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;
    private final UserMapper userMapper;
    private final ImageServiceImpl imageService;
    private final PasswordEncoder encoder;

    @Value("${path.to.photos.folder}")
    private String photoDir;


    public UserServiceImpl(UserRepository userRepository, PhotoRepository photoRepository, UserMapper userMapper, ImageServiceImpl imageService, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.photoRepository = photoRepository;
        this.userMapper = userMapper;
        this.imageService = imageService;
        this.encoder = encoder;
    }


    @Override
    public void setPassword(NewPassword newPass, Authentication authentication) {
        //получаем в переменную старый пароль
        String oldPassword = newPass.getCurrentPassword();
        //получаем в переменную новый пароль и кодируем его
        String encodeNewPassword = encoder.encode(newPass.getNewPassword());
        //Находим в БД сущность авторизованного пользователя используя логин из authentication
        //проверка на null не нужна, т.к. тот факт, что пользователь авторизовался,
        //говорит о том, что он есть в БД
        UserEntity userEntity = userRepository.findUserEntityByUserName(authentication.getName());
        //проверяем совпадают ли старый пароль, введенный пользователем, и пароль сохраненный в БД
        if (!encoder.matches(oldPassword, userEntity.getPassword())) {
            throw new PasswordIsNotMatchException("Пароли не совпадают");
        } else {
            //пароли совпадают, а значит устанавливаем новый пароль в соответствующее поле сущности
            userEntity.setPassword(encodeNewPassword);
        }
        //сохраняем сущность в БД
        userRepository.save(userEntity);
    }


    @Transactional
    @Override
    public UserEntity getUser(String username) {
        UserEntity user = userRepository.findUserEntityByUserName(username);
        if (user == null) {
            throw new UserNotFoundException("Пользователя с таким логином в базе данных нет");
        }
        return user;
    }


    @Transactional
    @Override
    public UserEntity updateUser(UpdateUser updateUser, Authentication authentication) {
        //Получаем логин авторизованного пользователя из БД
        String userName = authentication.getName();
        //Находим данные авторизованного пользователя
        UserEntity user = userRepository.findUserEntityByUserName(userName);
        //Меняем данные пользователя на данные из DTO updateUser
        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setPhone(updateUser.getPhone());
        //сохраняем измененные данные в БД
        userRepository.save(user);
        return user;
    }

    @Transactional
    @Override
    public void updateUserImage(MultipartFile image, Authentication authentication) throws IOException {

        //достаем пользователя из БД
        UserEntity userEntity = userRepository.findUserEntityByUserName(authentication.getName());

        //заполняем поля и возвращаем
        userEntity = (UserEntity) imageService.updateEntitiesPhoto(image, userEntity);

        //сохранение сущности user в БД
        userRepository.save(userEntity);
    }
}