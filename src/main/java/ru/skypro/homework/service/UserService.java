package ru.skypro.homework.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.User;
import org.springframework.stereotype.Service;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.ImageEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.UserRepository;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ImageService imageService;

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id)
                .flatMap(userMapper::toDto);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .flatMap(userMapper::toDto);
    }

    public Optional<User> updateUser(User userDto) {
        return userRepository.findById(userDto.getId())
                .map(entity -> {
                    entity.setFirstName(userDto.getFirstName());
                    entity.setLastName(userDto.getLastName());
                    entity.setPhone(userDto.getPhone());
                    entity.setImage(userDto.getImage());
                    return userRepository.save(entity);
                })
                .flatMap(userMapper::toDto);
    }

    public boolean changePassword(String email, String oldPassword, String newPassword) {
        return userRepository.findByEmail(email)
                .map(user -> {
                    if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
                        return false;
                    }
                    user.setPassword(passwordEncoder.encode(newPassword));
                    userRepository.save(user);
                    return true;
                })
                .orElse(false);
    }

    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public void updateUserAvatar(Integer userId, MultipartFile file) throws IOException {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ImageEntity avatar = imageService.saveImage(file);

        if (user.getAvatar() != null) {
            imageService.deleteImage(user.getAvatar().getId());
        }

        user.setAvatar(avatar);
        userRepository.save(user);
    }

    public UserEntity getUserEntityByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}