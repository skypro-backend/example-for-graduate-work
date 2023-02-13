package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.model.dto.UserDto;
@Service
public interface UsersService {

    UserDto update(UserDto user,String email);
    void updateUserImage();
    UserDto getUser(String email);
}
