package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.ImagesRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UsersService;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final ImagesRepository imagesRepository;
    private final ImageService imageService;

    @Override
    @Transactional
    public void setPassword(String currentPassword, String newPassword, String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (passwordEncoder.matches(currentPassword, userEntity.getPassword())) {
            userEntity.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(userEntity);
        } else {
            throw new BadCredentialsException("Incorrect current password");
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
        userMapper.updateUserToUserEntity(updateUser, userEntity);
        userRepository.save(userEntity);
        return userMapper.userEntityToUpdateUser(userEntity);
    }

    @Override
    @Transactional
    public byte[] updateUserImage(MultipartFile file, String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        Image image = userEntity.getImage();
        image = imageService.updateImage(file, image);
        userEntity.setImage(image);
        imagesRepository.save(image);
        userRepository.save(userEntity);
        return image.getImage();
    }

    @Override
    @Transactional
    public void createUser(UserDetails myDatabaseUserDetails) {
        MyDatabaseUser myDatabaseUser = ((MyDatabaseUserDetails) myDatabaseUserDetails).getMyDatabaseUser();
        UserEntity userEntity = userMapper.myDatabaseUserToUserEntity(myDatabaseUser);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public boolean userExists(String username) {
        return userRepository.findByUsername(username) != null;
    }
}
