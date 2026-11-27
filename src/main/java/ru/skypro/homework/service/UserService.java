package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.ResourceNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.CustomUserDetailsManager;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CustomUserDetailsManager userDetailsManager;

    @Transactional
    public void setPassword(NewPassword newPassword) {
        userDetailsManager.changePassword(newPassword.getCurrentPassword(), newPassword.getNewPassword());
    }

    @Transactional(readOnly = true)
    public User getCurrentUser(Authentication authentication) {
        UserEntity user = getUserEntityByEmail(authentication.getName());
        return userMapper.toDto(user);
    }

    @Transactional
    public UpdateUser updateUser(UpdateUser updateUser, Authentication authentication) {
        UserEntity user = getUserEntityByEmail(authentication.getName());
        userMapper.updateEntityFromDto(updateUser, user);
        userRepository.save(user);
        return updateUser;
    }

    @Transactional
    public void updateUserImage(MultipartFile image, Authentication authentication) {
        getUserEntityByEmail(authentication.getName());
    }

    public UserEntity getUserEntityByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден: " + email));
    }
}
