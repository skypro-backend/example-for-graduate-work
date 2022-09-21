package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.skypro.homework.models.dto.CreateUserDto;
import ru.skypro.homework.models.dto.NewPasswordDto;
import ru.skypro.homework.models.dto.UserDto;
import ru.skypro.homework.models.entity.User;
import ru.skypro.homework.models.mappers.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public CreateUserDto addUser(CreateUserDto user) {
        log.info("Trying to add new user");
        User response = userRepository.save(userMapper.toUser(user));
        log.info("The user with id = {} was saved ", response.getId());

        return userMapper.toCreateUserDto(response);
    }

    @Override
    public List<UserDto> getUsers() {
        log.info("Trying to get all users");

        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        log.info("Trying to update the user with id = {}", user.getId());
        getUser(user.getId());
        log.info("The user is found, updating...");
        User response = userRepository.save(userMapper.toUser(user));
        log.info("The user with id = {} was updated ", response.getId());

        return userMapper.toUserDto(response);
    }

    @Override
    public NewPasswordDto setPassword(NewPasswordDto newPassword) {
        log.info("trying to set new password");
        throw new RuntimeException("The feature isn't implemented yet");
        //TODO: make the mapper
    }

    @Override
    public UserDto getUserDto(Integer id) {
        User user = getUser(id);

        return userMapper.toUserDto(user);
    }

    @Override
    public User getUser(Integer id) {
        log.info("trying to get the user with id = {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> {
            log.warn("The user with id = {} doesn't exist", id);
            throw new NotFoundException("The user with id = " + id + " doesn't exist");
        });
        log.info("The user with id = {} was found", id);

        return user;
    }
}
