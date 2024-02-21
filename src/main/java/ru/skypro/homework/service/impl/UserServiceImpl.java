package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.config.GetAuthentication;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.user.UpdateUser;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.IncorrectPasswordException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AvatarService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final AvatarService avatarService;
    private final PasswordEncoder encoder;

    @Override
    public void setPassword(NewPassword newPassword, Authentication authentication) {
        User user = new GetAuthentication().getAuthenticationUser(authentication.getName());
        if (encoder.matches(newPassword.getCurrentPassword(), user.getPassword())) {
            user.setPassword(encoder.encode(newPassword.getNewPassword()));
            repository.save(user);
            return;
        }
        throw new IncorrectPasswordException("Неверный пароль");

    }

    @Override
    public UserDTO getUser(Authentication authentication) {
        return null;
    }

    @Override
    public UpdateUser updateUserInfo(UpdateUser update, Authentication authentication) {
        return null;
    }

    @Override
    public void updateUserAvatar(MultipartFile image, Authentication authentication) throws IOException {

    }
}
