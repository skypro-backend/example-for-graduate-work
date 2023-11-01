package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateUser;
import ru.skypro.homework.dto.user.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.util.List;


@Service
public class UserServiceImpl  implements UserService {


    @Override
    public boolean changePassword(NewPassword newPassword, String userName) {
        return false;
    }

    @Override
    public User retrieveAuthorizedInformation(String userName) {
        return null;
    }

    @Override
    public UpdateUser patchAuthorizedUserInformation(UpdateUser updateUser, String UserName) {
        return null;
    }

    @Override
    public boolean patchAuthorizedUserPicture(MultipartFile image, String username) {
        return false;
    }
}
