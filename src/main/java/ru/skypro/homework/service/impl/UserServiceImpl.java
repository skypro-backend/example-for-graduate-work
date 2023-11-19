package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.exception.PasswordChangeException;
import ru.skypro.homework.mapper.NewPasswordMapper;
import ru.skypro.homework.mapper.UpdateUserMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.MyUserPrincipal;
import ru.skypro.homework.service.UserService;

import java.awt.*;


@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public UpdateUserDto update(UpdateUserDto updateUserDto, Authentication authentication) {
        User user = findUserByUsername(authentication);
        UpdateUserMapper.INSTANCE.toModel(updateUserDto, user);
        userRepository.save(user);
        log.info("пользователь обновлен");
        return UpdateUserMapper.INSTANCE.toDTO(user);

    }

    @Override
    public NewPasswordDto setPassword(NewPasswordDto newPasswordDto, Authentication authentication) {
        User user = findUserByUsername(authentication);
        String currentPassword = user.getPassword();
        if (encoder.matches(newPasswordDto.getCurrentPassword(), currentPassword)) {
            user.setPassword(encoder.encode(newPasswordDto.getNewPassword()));
            userRepository.save(user);
            log.info("пароль успешно обновлен");
            NewPasswordMapper.INSTANCE.toDto(user);
            return newPasswordDto;
        } else {
            throw new PasswordChangeException("ошибка изменения пароля");
        }
    }

    @Override
    public UserDto getUserDto(Authentication authentication) {
        User user = findUserByUsername(authentication);
        log.info("пользователь найден");
        return UserMapper.INSTANCE.toDto(user);
    }

    @Override
    public String updateImage(MultipartFile image, Authentication authentication) {
        User user = findUserByUsername(authentication);
        user.setUserImage(image.getName());
        log.info("изображение обновлено");
        return "изображение обновлено";
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Пользователь '%s' не найден", username)));
        return new MyUserPrincipal(user);
    }

    private User findUserByUsername(Authentication authentication) {
        return userRepository.findByUserName(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Пользователь '%s' не найден", authentication.getName())));

    }
}
