package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.skypro.homework.models.dto.CreateUserDto;
import ru.skypro.homework.models.dto.NewPasswordDto;
import ru.skypro.homework.models.dto.UserDto;
import ru.skypro.homework.models.entity.User;
import ru.skypro.homework.models.mappers.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import javax.transaction.NotSupportedException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public CreateUserDto addUser(CreateUserDto user) {
        logger.info("Trying to add new user");
        User response = userRepository.save(userMapper.toUser(user));
        logger.info("The user with id = {} was saved ", response.getId());
        return userMapper.toCreateUserDto(response);
    }

    @Override
    public List<UserDto> getUsers() {
        logger.info("Trying to get all users");
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        logger.info("Trying to update the user with id = {}", user.getId());
        checkOnExistingUser(user.getId());
        logger.info("The user is found, updating...");
        User response = userRepository.save(userMapper.toUser(user));
        logger.info("The user with id = {} was updated ", response.getId());
        return userMapper.toUserDto(response);
    }

    @Override
    public NewPasswordDto setPassword(NewPasswordDto newPassword) {
        logger.info("trying to set new password");
        throw new RuntimeException("The feature isn't implemented yet");
        //TODO: make the mapper
    }

    @Override
    public UserDto getUser(Integer id) {
        logger.info("trying to get the user with id = {}", id);
        User user = userRepository.findById(id).orElseThrow();
        logger.info("The user with id = {} was found", id);
        return userMapper.toUserDto(user);
    }

    private void checkOnExistingUser(Integer id) {
        if (userRepository.findById(id).isEmpty()) {
            logger.warn("The user with id = {} doesn't exist", id);
            throw new NotFoundException("The user with id = " + id + " doesn't exist");
        }
    }
}
