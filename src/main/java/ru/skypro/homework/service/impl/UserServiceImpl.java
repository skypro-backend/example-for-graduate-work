package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AvatarDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mapping.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public User saveUsers(User user){
        userRepository.save(user);
        return user;
    }
    public User findByUserName(String username){
        return userRepository.findByEmail(username).orElseThrow(null);
    }

    @Override
    public UserDto saveUser(UserDto user) {
        logger.info("saving user");

        return null;
    }

    @Override
    public UserDto deleteUser(UserDto user) {
        logger.info("deleting user");
        return null;
    }

    @Override
    public UserDto getInfoUser() {
        return null;
    }

    @Override
    public UpdateUserDto setInfoUser(UpdateUserDto updateUser) {
        return null;
    }

    @Override
    public AvatarDto setAvatar(MultipartFile avatar) throws IOException {
        return null;
    }


}
