package ru.skypro.homework.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.dto.UserUpdate;
import ru.skypro.homework.entity.User;

import java.util.Collection;
import java.util.Optional;

public interface UserService {

    void registerNewUser(Register register, String encodePassword);
    void updatePassword(User user);
    Optional<UserDto> getUserByDtoByLogin(String userLogin);
    Optional<UserUpdate> updateUserByUserUpdate(String userLogin, UserUpdate userUpdate);
    Optional<User> getUserByLogin(String userLogin);
    Optional<User> changeImage(String userLogin, MultipartFile multipartFile);
    Collection<UserDetails> getAllUserDetails();

}
