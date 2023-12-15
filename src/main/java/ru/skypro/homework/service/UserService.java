package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Avatar;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface UserService {

    List<User> users = new ArrayList<>();
    User saveUser(User user);
    User deleteUser(User user);
    User getInfoUser();
    UpdateUser setInfoUser(UpdateUser updateUser);
    Avatar setAvatar(MultipartFile avatar) throws IOException;


}
