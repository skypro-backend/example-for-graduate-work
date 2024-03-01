package ru.skypro.homework.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.util.SecurityUtil;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ImageService imageService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ImageService imageService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.imageService = imageService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User updatePassword(NewPassword dto) {
        User user = SecurityUtil.getUserDetails().getUser();
        if (!passwordEncoder.matches(dto.getCurrentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Wrong current password");
        }
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getMe() {
        return SecurityUtil.getUserDetails().getUser();
    }

    @Override
    public User updateMe(UpdateUser dto) {
        User user = SecurityUtil.getUserDetails().getUser();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhone(dto.getPhone());
        return userRepository.save(user);
    }

    @Override
    public User updateMyImage(MultipartFile imageFile) {
        User user = SecurityUtil.getUserDetails().getUser();
        user.setImage(imageService.saveImage(imageFile));
        return userRepository.save(user);
    }
}