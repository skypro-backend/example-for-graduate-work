package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserAvatarRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;
import org.springframework.security.access.AccessDeniedException;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder encoder;
    private final ImageService imageService;
    private final UserRepository repository;
    private final UserAvatarRepository userAvatarRepository;
    private final UserMapper mapper;
    private final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Value("${avatar.storage.directory}")
    private String avatarStorageDirectory;

    @Override
    public User find() {
        var username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        return find(username);
    }

    private User find(String username) {
        return repository.findByUsername(username)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void createUser(RegisterDto registerDto) {
        var user = find(registerDto.getUsername());
        mapper.update(registerDto, user);
        try {
            repository.save(user);
            logger.info("Пользователь успешно сохранен: {}", user.getUsername());
        } catch (Exception e) {
            logger.error("Не удалось сохранить пользователя {}: {}", user.getUsername(), e.getMessage());
            throw new RuntimeException("Не удалось сохранить пользователя.", e);
        }
    }

    @Override
    public UserDto getUser() {
        return null;
    }

    @Override
    public UserDto getUser(String username) {
        var user = find(username);
        return mapper.userToUserDto(user);
    }


    @Override
    public void updatePassword(NewPasswordDto newPasswordDto) {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        updatePassword(newPasswordDto, username);
    }

    @Override
    public void updatePassword(NewPasswordDto newPasswordDto, String username) {
        Optional<User> user = repository.findByUsername(username);
        if (encoder.matches(newPasswordDto.getCurrentPassword(), user.get().getPassword()) &&
                newPasswordDto.getNewPassword() != null &&
                !newPasswordDto.getNewPassword().equals(newPasswordDto.getCurrentPassword())) {
            user.get().setPassword(encoder.encode(newPasswordDto.getNewPassword()));
            repository.save(user.get());
        }
    }

    @Override
    public void updateUser(UpdateUserDto updateUserDto) {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        updateUser(updateUserDto, username);
    }

    @Override
    public void updateUser(UpdateUserDto updateUserDto, String username) {
        var user = find(username);
        mapper.update(updateUserDto, user);
        repository.save(user);
    }

    @Override
    public void update(MultipartFile image) {
        var user = find();
        String filename;
        try {
            filename = imageService.create(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        user.setImage("/users/images/" + filename);
        repository.save(user);
    }

    @Override
    public void saveUserAvatar(@RequestPart MultipartFile file) {
        try {
            File directory = new File(avatarStorageDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                User user = (User) authentication.getPrincipal();
                byte[] avatarData = file.getBytes();

                String fileName = "avatar_" + user.getId() + ".jpg";

                String filePath = avatarStorageDirectory + fileName;

                try (OutputStream outputStream = new FileOutputStream(filePath)) {
                    outputStream.write(avatarData);
                }

                user.setAvatarPath(filePath);
                repository.save(user);
            } else {
                throw new AccessDeniedException("В доступе отказано: Пользователь не прошел проверку подлинности.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось сохранить аватар", e);
        }
    }

    @Override
    public void saveUserAvatar(Authentication authentication, MultipartFile image) {
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            var user = find(username);

            try {
                String filename = imageService.create(image);
                String imagePath = "/users/images/" + filename;
                user.setImage(imagePath);
                repository.save(user);
            } catch (IOException e) {
                throw new RuntimeException("Не удалось сохранить аватар", e);
            }
        } else {
            throw new AccessDeniedException("В доступе отказано: Пользователь не прошел проверку подлинности.");
        }
    }

    @Override
    public void updateUserImage(MultipartFile image, String username) {
        if (isUserAllowedToUpdateImage(username)) {
            var user = find(username);

            try {
                String filename = imageService.create(image);
                String imagePath = "/users/images/" + filename;
                user.setImage(imagePath);
                repository.save(user);
            } catch (IOException e) {
                throw new RuntimeException("Не удалось обновить изображение пользователя", e);
            }
        } else {
            throw new AccessDeniedException("Недостаточно прав для обновления изображения пользователя");
        }
    }


    @Override
    public byte[] getAvatarImage(String filename) {
        try {
            File imageFile = new File(avatarStorageDirectory + filename);
            if (imageFile.exists()) {
                Path imagePath = imageFile.toPath();
                return Files.readAllBytes(imagePath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось получить аватар", e);
        }

        return null;
    }


    @Override
    public boolean isUserAllowedToUpdate(UpdateUserDto updateUserDto) {

        return true;
    }


    @Override
    public boolean isUserAllowedToSetPassword(String username) {

        return true;
    }

    @Override
    public boolean isUserAllowedToUpdate(String username, UpdateUserDto updateUserDto) {

        return true;
    }

    @Override
    public void updateUser(String username, UpdateUserDto updateUserDto) {
        var user = find(username);
        mapper.update(updateUserDto, user);
        repository.save(user);
    }

    @Override
    public void updatePassword(String username, NewPasswordDto newPasswordDto) {
        Optional<User> user = repository.findByUsername(username);
        if (encoder.matches(newPasswordDto.getCurrentPassword(), user.get().getPassword()) &&
                newPasswordDto.getNewPassword() != null &&
                !newPasswordDto.getNewPassword().equals(newPasswordDto.getCurrentPassword())) {
            user.get().setPassword(encoder.encode(newPasswordDto.getNewPassword()));
            repository.save(user.get());
        }
    }

    @Override
    public boolean isUserAllowedToUpdateImage(String username) {

        return true;
    }

    @Override
    public void updateImage(String username, MultipartFile image) {
        if (isUserAllowedToUpdateImage(username)) {
            try {
                var user = find(username);
                String filename = imageService.create(image);
                user.setImage("/users/images/" + filename);
                repository.save(user);
            } catch (IOException e) {
                throw new RuntimeException("Не удалось обновить изображение пользователя", e);
            }
        } else {
            throw new AccessDeniedException("Недостаточно прав для обновления изображения пользователя");
        }
    }
}
