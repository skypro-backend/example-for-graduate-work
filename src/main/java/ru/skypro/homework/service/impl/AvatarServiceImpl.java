package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.AvatarEntity;
import ru.skypro.homework.service.AvatarService;
import ru.skypro.homework.service.UserService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

@Service
@Slf4j
public class AvatarServiceImpl implements AvatarService {
    private final String avatarsDir;
    private final AvatarRepository avatarRepository;
    private final UserService userService;

    public AvatarServiceImpl(AvatarRepository avatarRepository,
                         @Value("${path.to.avatars.folder}") String avatarsDir,
                             UserService userService) {
        this.avatarRepository = avatarRepository;
        this.avatarsDir = avatarsDir;
        this.userService = userService;
    }
    @Override
    public void updateAvatar(Integer id, MultipartFile image, Authentication authentication) throws IOException {
        log.info("Запущен метод сервиса {}", LoggingMethodImpl.getMethodName());
        if (userService.getUser(authentication).getId().equals(id)) {
            AvatarEntity avatar = new AvatarEntity();
            Path path = Path.of(avatarsDir);
            if (!path.toFile().exists()) {
                Files.createDirectories(path);
            }
            var dotIndex = Objects.requireNonNull(image.getOriginalFilename()).lastIndexOf('.');
            var ext = image.getOriginalFilename().substring(dotIndex + 1);
            var photoPath = avatarsDir + "/" + userService.getUser(authentication).getUserName() + "." + ext;
            try (var in = image.getInputStream();
                 var out = new FileOutputStream(photoPath)) {
                in.transferTo(out);
            }
            avatar.setFilePath(photoPath);
            avatar.setData(image.getBytes());
            avatar.setFileSize(image.getSize());
            avatar.setMediaType(image.getContentType());
            avatarRepository.save(avatar);
        }
    }
}
