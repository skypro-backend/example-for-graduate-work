package ru.skypro.homework.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ImageService imageService;

    public UserService(
            UserRepository userRepository,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder,
            ImageService imageService
    ) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.imageService = imageService;
    }

    public User getUser(String username) {
        return userMapper.toDto(
                getUserEntity(username)
        );
    }

    public UpdateUser updateUser(
            String username,
            UpdateUser dto
    ) {
        UserEntity entity = getUserEntity(username);

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhone(dto.getPhone());

        userRepository.save(entity);

        UpdateUser result = new UpdateUser();
        result.setFirstName(entity.getFirstName());
        result.setLastName(entity.getLastName());
        result.setPhone(entity.getPhone());

        return result;
    }

    public void changePassword(
            String username,
            NewPassword dto
    ) {
        UserEntity entity = getUserEntity(username);

        if (!passwordEncoder.matches(
                dto.getCurrentPassword(),
                entity.getPassword()
        )) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Current password is incorrect"
            );
        }

        entity.setPassword(
                passwordEncoder.encode(
                        dto.getNewPassword()
                )
        );

        userRepository.save(entity);
    }

    public void updateImage(
            String username,
            MultipartFile image
    ) {
        UserEntity entity = getUserEntity(username);

        String fileName =
                imageService.saveImage(image);

        entity.setImage(
                "/images/" + fileName
        );

        userRepository.save(entity);
    }

    private UserEntity getUserEntity(
            String username
    ) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "User not found"
                        )
                );
    }
}