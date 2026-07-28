package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.utils.SecurityUtils;
import ru.skypro.homework.service.ImageStorageService;
import ru.skypro.homework.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ImageStorageService imageStorageService;
    private final SecurityUtils securityUtils;
    private final UserDetailsManager userDetailsManager;

    @Override
    public User getUser(Authentication authentication) {
        return userMapper.toDto(securityUtils.getCurrentUser(authentication));
    }

    @Override
    public UpdateUser updateUser(UpdateUser dto, Authentication authentication) {
        ru.skypro.homework.entity.User user = securityUtils.getCurrentUser(authentication);
        userMapper.updateEntityFromDto(dto, user);
        userRepository.save(user);
        return dto;
    }

    @Override
    public void updateUserImage(MultipartFile image, Authentication authentication) {
        ru.skypro.homework.entity.User user = securityUtils.getCurrentUser(authentication);
        String path = imageStorageService.save(image, "user_" + user.getId());
        user.setImage(path);
        userRepository.save(user);
    }

    @Override
    public void setPassword(NewPassword dto, Authentication authentication) {
        userDetailsManager.changePassword(dto.getCurrentPassword(), dto.getNewPassword());
    }
}
