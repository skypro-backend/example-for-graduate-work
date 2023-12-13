package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateUser;
import ru.skypro.homework.dto.user.User;

public interface UserService {

    public boolean changePassword(NewPassword newPassword, String userName);
    public User retrieveAuthorizedInformation(String userName);
    public UpdateUser patchAuthorizedUserInformation(UpdateUser updateUser, String UserName);
    void patchAuthorizedUserPicture(MultipartFile image, String username);

}
