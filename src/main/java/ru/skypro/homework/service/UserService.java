package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UserDto;

import java.io.IOException;
/**
 * CRUD-methods for managing Users on platform
 */
public interface UserService {

    /**
     * Method to get user my authentication data
     * @param authentication authentication data of the user
     * @return Return the found user
     */
    UserDto get(Authentication authentication);

    /**
     * Method to Update the old user info
     * @param userDto DTO of the User
     * @param authentication authentication data of the user
     * @return Returns updated user
     */

    UserDto update(UserDto userDto, Authentication authentication);

    /**
     * Method to update users password
     * @param newPassword DTO for the new password
     * @param authentication authentication data of the user
     */

    void updatePassword(NewPassword newPassword, Authentication authentication);

    /**
     * Method to update users avatar
     * @param avatar Avatar file
     * @param authentication authentication data of the user
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     */

    void updateAvatar(MultipartFile avatar, Authentication authentication) throws IOException;
}
