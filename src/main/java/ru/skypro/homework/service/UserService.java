package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.projections.NewPassword;
import ru.skypro.homework.projections.Register;
import ru.skypro.homework.projections.UpdateUser;

public interface UserService {
    UserModel find();

    void createUser(Register register);

    UserDTO getUser();

    void updatePassword(NewPassword newPassword);

    void updateUser(UpdateUser updateUserDto);

    void update(MultipartFile image);
}
