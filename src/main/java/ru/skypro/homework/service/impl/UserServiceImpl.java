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
import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.AccessDeniedException;
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


    /**
     * find() is a public method used to get the current user
     * @author radyushinaalena
     */
    @Override
    public User find() {
        var username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        return find(username);
    }


    /**
     * find(String username) is a public method used to get user information from a repository
     * @author AlexBoko
     */
    private User find(String username) {
        return repository.findByUsername(username)
                .orElseThrow(EntityNotFoundException::new);
    }


    /**
     * createUser(RegisterDto registerDto) is a public method used to create a user
     * @author AlexBoko + radyushinaalena
     */
    @Override
    public void createUser(RegisterDto registerDto) {
        var user = find(registerDto.getUsername());
        mapper.update(registerDto, user);
        try {
            repository.save(user);
            logger.info("Пользователь успешно сохранен.", user.getUsername());
        } catch (Exception e) {
            logger.error("Не удалось сохранить пользователя{}: {}", user.getUsername(), e.getMessage());
            throw new RuntimeException("Не удалось сохранить пользователя.", e);
        }
    }


    /**
     * getUser() is a public method used to read user information
     * @author radyushinaalena
     */
    @Override
    public UserDto getUser() {
        var user = find();
        return mapper.userToUserDto(user);
    }


    @Override
    public void updatePassword(NewPasswordDto newPasswordDto) {

    }


    /**
     * updatePassword(NewPasswordDto newPasswordDto, String username) is a public method used to update the password
     * @author AlexBoko
     */
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


    /**
     * updateUser(UpdateUserDto updateUserDto) is a public method used to update user information
     * @author radyushinaalena
     */
    @Override
    public void updateUser(UpdateUserDto updateUserDto) {
        var user = find();
        mapper.update(updateUserDto, user);
        repository.save(user);
    }


    /**
     * update(MultipartFile image) is a public method used to update a user's avatar
     * @author radyushinaalena + AlexBoko
     */
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


    @Value("${avatar.storage.directory}")
    private String avatarStorageDirectory;


    /**
     * saveUserAvatar(@RequestPart MultipartFile file) is a public method used to save a user's avatar
     * @author AlexBoko
     */
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

    /**
     * saveUserAvatar(Authentication authentication, MultipartFile image) is a public method used to save a user's avatar
     * @author AlexBoko
     */
    @Override
    public void saveUserAvatar(Authentication authentication, MultipartFile image) {

    }
}