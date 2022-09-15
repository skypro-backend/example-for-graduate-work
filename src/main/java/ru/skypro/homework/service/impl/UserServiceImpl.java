package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.models.dto.CreateUserDto;
import ru.skypro.homework.models.dto.NewPasswordDto;
import ru.skypro.homework.models.dto.UserDto;
import ru.skypro.homework.models.entity.User;
import ru.skypro.homework.models.mappers.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public CreateUserDto addUser(CreateUserDto user) {
        User newUser = userMapper.toUser(user);
        userRepository.save(newUser);
        return user;
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(e -> userMapper.toUserDto(e))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        if(userRepository.existsById(user.getId())) {
            userRepository.save(userMapper.toUser(user));
            return user;
        }
        return null;
    }

    @Override
    public NewPasswordDto setPassword(NewPasswordDto newPassword) {
        return null;
    }

    @Override
    public UserDto getUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return userMapper.toUserDto(user.get());
        }
        return null;
    }

}
