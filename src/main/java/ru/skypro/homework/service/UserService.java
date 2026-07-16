package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserDto register(Register register) {
        UserEntity entity = userMapper.toEntity(register);
        entity = userRepository.save(entity);
        log.info("Зарегистрирован новый пользователь: {}", entity.getEmail());
        return userMapper.toDto(entity);
    }


    @Transactional(readOnly = true)
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }


    @Transactional(readOnly = true)
    public UserDto getUserProfile(String email) {
        UserEntity entity = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден: " + email));
        return userMapper.toDto(entity);
    }


    public UserDto updateUser(String email, UpdateUserDto updateUserDto) {
        UserEntity entity = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден: " + email));
        userMapper.updateEntity(entity, updateUserDto);
        entity = userRepository.save(entity);
        log.info("Обновлён профиль пользователя: {}", entity.getEmail());
        return userMapper.toDto(entity);
    }


    public void changePassword(String email, NewPasswordDto newPasswordDto) {
        UserEntity entity = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден: " + email));
        if (!entity.getPassword().equals(newPasswordDto.getCurrentPassword())) {
            throw new IllegalArgumentException("Неверный текущий пароль");
        }
        entity.setPassword(newPasswordDto.getNewPassword());
        userRepository.save(entity);
        log.info("Пароль изменён для пользователя: {}", email);
    }
}