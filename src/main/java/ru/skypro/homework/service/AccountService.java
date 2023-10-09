package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.account.NewPassword;
import ru.skypro.homework.dto.account.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AccountService {
    boolean updatePassword(NewPassword newPassword);
    User getInfoAboutUser();
    User patchInfoAboutUser(User user);
    boolean updateUserAvatar(MultipartFile image) throws IOException;
    boolean downloadAvatarFromDB(int userId, HttpServletResponse response) throws IOException;
}
