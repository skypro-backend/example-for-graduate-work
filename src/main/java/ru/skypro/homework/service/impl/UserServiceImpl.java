package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.entity.UserImage;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder encoder;
    private final UserMapper userMapper;
    private final ImageService imageService;
    private final UserRepository userRepository;

    @Override
    public void setPassword(final String currentPassword, final String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.findByLogin(authentication.getName())
                .filter(user -> encoder.matches(currentPassword, user.getPassword()))
                .map(user -> {
                    user.setPassword(encoder.encode(newPassword));
                    return userRepository.save(user);
                }).orElseThrow(() -> new BadCredentialsException("Неверный текущий пароль"));
    }

    @Override
    public UserDto getAuthorizedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByLogin(authentication.getName())
                .map(userMapper::toDto)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    @Override
    public UpdateUserDto updateUser(final UpdateUserDto updateUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByLogin(authentication.getName()).map(user -> {
            user.setFirstName(updateUser.firstName());
            user.setLastName(updateUser.lastName());
            user.setPhone(updateUser.phone());
            var updatedUser = userRepository.save(user);
            return new UpdateUserDto(updatedUser.getFirstName(), updatedUser.getLastName(), updatedUser.getPhone());
        }).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    @Override
    public void UpdateUserImage(final MultipartFile file) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByLogin(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        UserImage image = (UserImage) imageService.updateImage(file, new UserImage());
        user.setUserImage(image);
        userRepository.save(user);
    }
}