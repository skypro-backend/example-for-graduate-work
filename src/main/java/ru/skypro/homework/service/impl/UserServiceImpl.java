package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.config.GetAuthentication;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.exception.IncorrectPasswordException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.Avatar;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AvatarService;
import ru.skypro.homework.service.UserService;
import javax.transaction.Transactional;
import java.io.IOException;

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
    public UserDto getUser(Authentication authentication) {
        User user = new GetAuthentication().getAuthenticationUser(authentication.getName());
        return mapper.userToUserDto(user);
    }

    @Override
    public UpdateUser updateUserInfo(UpdateUser update, Authentication authentication) {
        User user = new GetAuthentication().getAuthenticationUser(authentication.getName());
        user.setFirstName(update.getFirstName());
        user.setLastName(user.getLastName());
        user.setPhone(user.getPhone());
        repository.save(user);
        return update;
    }

    @Override
    @Transactional
    public void updateUserAvatar(MultipartFile image, Authentication authentication) {
        User user = new GetAuthentication().getAuthenticationUser(authentication.getName());
        Avatar imageFile = user.getAvatar();
        user.setAvatar(avatarService.uploadAvatar(image));
        if (imageFile != null) {
            avatarService.removeAvatar(imageFile);
        }
        repository.save(user);
    }

}
