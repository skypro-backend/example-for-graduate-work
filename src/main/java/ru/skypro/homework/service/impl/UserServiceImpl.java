package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.NewPasswordDto;
import ru.skypro.homework.dto.user.UpdateUserDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.exception.ForbiddenException;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ImageService imageService;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUser(String email) {
        return userMapper.toDto(getUserByEmail(email));
    }

    @Override
    @Transactional
    public UpdateUserDto updateUser(String email, UpdateUserDto updateUserDto) {
        User user = getUserByEmail(email);
        userMapper.updateEntity(updateUserDto, user);
        userRepository.save(user);
        return userMapper.toUpdateDto(user);
    }

    @Override
    @Transactional
    public void updateUserImage(String email, MultipartFile image) {
        User user = getUserByEmail(email);
        String imagePath = imageService.saveUserImage(user.getId(), image);
        user.setImage(imagePath);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void changePassword(String email, NewPasswordDto newPasswordDto) {
        User user = getUserByEmail(email);
        if (!passwordEncoder.matches(newPasswordDto.getCurrentPassword(), user.getPassword())) {
            throw new ForbiddenException();
        }
        user.setPassword(passwordEncoder.encode(newPasswordDto.getNewPassword()));
        userRepository.save(user);
    }
}
