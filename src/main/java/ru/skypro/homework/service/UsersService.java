package ru.skypro.homework.service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

public interface UsersService {
    void setPassword(NewPassword password);
    User getUser();
    UpdateUser updateUser(UpdateUser updateUser);
    void updateUserImage(MultipartFile image);


}
