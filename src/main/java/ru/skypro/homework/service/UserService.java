package ru.skypro.homework.service;


import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.ImageDTO;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateUser;
import ru.skypro.homework.dto.user.User;

/**
 * The interface with methods for updating and getting user's account
 * @author Sulaeva Marina
 */
public interface UserService {
    void updatePassword(NewPassword newPassword, String username);
    User getInformation(String username);
    UpdateUser updateInformationAboutUser(UpdateUser updateUser, String username);
    ImageDTO updateImage (MultipartFile file, String username);

    byte [] getImage (String id);

}