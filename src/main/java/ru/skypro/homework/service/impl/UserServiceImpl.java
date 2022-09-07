package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.models.dto.CreateUserDto;
import ru.skypro.homework.models.dto.NewPasswordDto;
import ru.skypro.homework.models.dto.UserDto;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public CreateUserDto addUser(CreateUserDto user) {
        return null;
    }

    @Override
    public List<UserDto> getUsers() {
        return null;
    }

    @Override
    public UserDto updateUser(UserDto user) {
        return null;
    }

    @Override
    public NewPasswordDto setPassword(NewPasswordDto newPassword) {
        return null;
    }

    @Override
    public UserDto getUser(Integer id) {
        return null;
    }

}
