package ru.skypro.homework.service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CustomUserDetails;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

public interface UsersService {
    void setPassword(NewPassword password, CustomUserDetails userDetails);
    User getUser(CustomUserDetails userDetails);
    UpdateUser updateUser(UpdateUser updateUser, CustomUserDetails userDetails);
    void updateUserImage(MultipartFile image, CustomUserDetails userDetails);


}
