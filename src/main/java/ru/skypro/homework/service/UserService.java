package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.entities.Ad;
import ru.skypro.homework.entities.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.util.ImageUtil;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public User toEntity(ru.skypro.homework.dto.User DTO) {
        return User.builder().id(DTO.getId()).email(DTO.getEmail()).image(DTO.getImage()).phone(DTO.getPhone())
                .role(DTO.getRole()).firstname(DTO.getFirstname()).lastname(DTO.getLastname()).build();
    }

    public ru.skypro.homework.dto.User toDTO(User entity) {
        return ru.skypro.homework.dto.User.builder().id(entity.getId()).email(entity.getEmail()).image(entity.getImage())
                .role(entity.getRole()).phone(entity.getPhone()).firstname(entity.getFirstname())
                .lastname(entity.getLastname()).build();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void changePassword(NewPassword password, UserDetails userDetails) {
        User user = findByEmail(userDetails.getUsername());
        user.setPassword(password.getNewPassword());
        userRepository.save(user);
    }

    public ru.skypro.homework.dto.User getUser(UserDetails userDetails) {
        return toDTO(findByEmail(userDetails.getUsername()));
    }

    public void updateUser(UserDetails userDetails, UpdateUser updateUser) {
        User user = findByEmail(userDetails.getUsername());
        user.setFirstname(updateUser.getFirstname());
        user.setLastname(updateUser.getLastname());
        user.setPhone(updateUser.getPhone());
        userRepository.save(user);
    }

    public void updateUserImage(UserDetails userDetails, MultipartFile image) {
        User user = findByEmail(userDetails.getUsername());
        ImageUtil.deleteImage(user.getImage());
        user.setImage(ImageUtil.saveImage(image));
        userRepository.save(user);
    }
}
