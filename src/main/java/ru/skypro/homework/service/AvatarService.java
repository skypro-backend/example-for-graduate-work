package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Avatar;

import java.io.IOException;

public interface AvatarService {

    Avatar uploadAvatar(MultipartFile image) throws IOException;

    void removeAvatar(Avatar avatar);

    Avatar getAvatar(Long id);
}
