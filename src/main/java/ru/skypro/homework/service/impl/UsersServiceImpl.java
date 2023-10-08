package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.MyDatabaseUserDetails;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.ImagesRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UsersService;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final ImagesRepository imagesRepository;

    @Override
    @Transactional
    public void setPassword(String currentPassword, String newPassword, String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (passwordEncoder.matches(currentPassword, userEntity.getPassword())) {
            userEntity.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(userEntity);
        } else {
            throw new BadCredentialsException("Неверный текущий пароль");
        }
    }

    @Override
    @Transactional
    public User getUser(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        return userMapper.userEntityToUser(userEntity);
    }

    @Override
    @Transactional
    public UpdateUser updateUser(UpdateUser updateUser, String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        userEntity.setFirstName(updateUser.getFirstName());
        userEntity.setLastName(updateUser.getLastName());
        userEntity.setPhone(updateUser.getPhone());
        userRepository.save(userEntity);
        return userMapper.userEntityToUpdateUser(userEntity);
    }

    @Override
    @Transactional
    public byte[] updateUserImage(MultipartFile file, String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        Image image = userEntity.getImage();
        if (image == null) {
            image = new Image();
        }
        try {
            byte[] bytes = file.getBytes();
            image.setImage(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        userEntity.setImage(image);
        imagesRepository.save(image);
        userRepository.save(userEntity);
        return image.getImage();
    }

    @Override
    @Transactional
    public void createUser(UserDetails myDatabaseUserDetails) {
        UserEntity userEntity = ((MyDatabaseUserDetails) myDatabaseUserDetails).getUserEntity();
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public boolean userExists(String username) {
        return userRepository.findByUsername(username) != null;
    }
}
