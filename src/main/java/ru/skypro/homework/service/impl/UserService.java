package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUser() {
        return null;
    }

    public void updatePassword(NewPasswordDto newPasswordDto) {
    }

    public void updateUser(UpdateUserDto updateUserDto) {
    }

    public void updateUserImage(MultipartFile file) {

    }
}

