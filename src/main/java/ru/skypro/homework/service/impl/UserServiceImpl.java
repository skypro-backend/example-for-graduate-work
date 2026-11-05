package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.users.NewPasswordDto;
import ru.skypro.homework.dto.users.UpdateUserDto;
import ru.skypro.homework.dto.users.UserDto;
import ru.skypro.homework.exception.BadRequestException;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto getUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public UpdateUserDto updateUser(String email, UpdateUserDto updateUserDto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        user.setFirstName(updateUserDto.firstName());
        user.setLastName(updateUserDto.lastName());
        user.setPhone(updateUserDto.phone());
        userRepository.save(user);
        return updateUserDto;
    }

    @Override
    @Transactional
    public void updateUserImage(String email, MultipartFile image) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
    }

    @Override
    @Transactional
    public void setPassword(String email, NewPasswordDto newPasswordDto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        if(!passwordEncoder.matches(newPasswordDto.currentPassword(), user.getPassword())){
            throw new BadRequestException("Не верный текущий пароль");
        }
        user.setPassword(passwordEncoder.encode(newPasswordDto.newPassword()));
        userRepository.save(user);
    }
}
