package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Avatar;

public interface AvatarService {
    Avatar uploadAvatar(MultipartFile imageFile);

    void removeAvatar(Avatar avatar);

    Avatar getAvatar(Long id);
}
