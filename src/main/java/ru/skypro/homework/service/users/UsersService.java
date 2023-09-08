package ru.skypro.homework.service.users;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.dto.auth.RegisterDto;
import ru.skypro.homework.entity.users.User;
import ru.skypro.homework.exceptions.PasswordMatches;
import ru.skypro.homework.exceptions.UserNotFoundEx;
import ru.skypro.homework.exceptions.UserNotUpdatedEx;

import java.io.IOException;

public interface UsersService {
    void register(RegisterDto registerDto);

    User addNewPassword(Integer id, NewPasswordDto newPassword) throws PasswordMatches, UserNotFoundEx;

    User getUser(Integer id) throws UserNotFoundEx;


    User updateUser(Integer id, UpdateUserDto userDto) throws UserNotUpdatedEx;



    void uploadImage(Integer userId, MultipartFile multipartFile) throws IOException, UserNotFoundEx;
}
