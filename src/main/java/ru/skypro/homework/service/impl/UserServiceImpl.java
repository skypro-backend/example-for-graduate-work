package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.exception.IncorrectArgumentException;
import ru.skypro.homework.exception.IncorrectPasswordException;
import ru.skypro.homework.exception.UsernameNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AvatarServiceImpl avatarService;

    public User getByUsername(String userName) {
        return userRepository.findByUserNameIgnoreCase(userName)
                .orElseThrow(UsernameNotFoundException::new);
    }

    @Override
    public UserDto get(Authentication authentication) {
        log.info("Returning details for user: {}", authentication.getName());
        return UserMapper.INSTANCE.toDTO(getByUsername(authentication.getName()));
    }

    @Override
    public UserDto update(UserDto userDto, Authentication authentication) {
        if (userDto.getFirstName() == null || userDto.getFirstName().isBlank()
                || userDto.getLastName() == null || userDto.getLastName().isBlank()
                || userDto.getPhone() == null || userDto.getPhone().isBlank()) throw new IncorrectArgumentException();

        User user = getByUsername(authentication.getName());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        userRepository.save(user);
        log.debug("Updated data for user: {}", authentication.getName());
        return UserMapper.INSTANCE.toDTO(user);
    }

    @Override
    public void updatePassword(NewPassword newPassword, Authentication authentication) {
        User user = getByUsername(authentication.getName());
        if (!passwordEncoder.matches(newPassword.getCurrentPassword(), user.getPassword())) {
            throw new IncorrectPasswordException();
        }
        user.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
        userRepository.save(user);
        log.debug("Password updated for user: {}", authentication.getName());

    }

    @Override
    public void updateAvatar(MultipartFile avatar, Authentication authentication) throws IOException {
        User user = getByUsername(authentication.getName());

        if (user.getAvatar() != null) {
            avatarService.remove(user.getAvatar());
        }
        user.setAvatar(avatarService.uploadImage(avatar));
        userRepository.save(user);
        log.debug("Avatar updated for user: {}", authentication.getName());

    }

}
