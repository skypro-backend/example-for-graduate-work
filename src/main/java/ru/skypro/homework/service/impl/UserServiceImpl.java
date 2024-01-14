package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.users.NewPasswordDto;
import ru.skypro.homework.dto.users.UpdateUserDto;
import ru.skypro.homework.dto.users.UserDto;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final UserMapper mapper;

    private final PasswordEncoder encoder;

    private final ImageRepository imgRepository;

    @Override
    public void updatePassword(String userName, NewPasswordDto newPasswordDto) {

        UserEntity user = repository.findByUserName(userName).orElseThrow(() -> new NoSuchElementException("User is not registered"));
        user.setPassword(encoder.encode(newPasswordDto.getNewPassword()));
        repository.save(user);
    }

    @Override
    public UserDto getUser(String userName) {

        return repository.findByUserName(userName).stream()
                .map(mapper::userToDto)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("User is not registered"));
    }

    @Override
    public UpdateUserDto updateUser(String userName, UpdateUserDto updateUserDto) {

        UserEntity user = repository.findByUserName(userName).orElseThrow(() -> new NoSuchElementException("User is not registered"));
        user.setFirstName(updateUserDto.getFirstName());
        user.setLastName(updateUserDto.getLastName());
        user.setPhone(updateUserDto.getPhone());
        repository.save(user);

        return mapper.updateToDto(user);
    }

    @Override
    public void updatePhoto(String userName, MultipartFile image) {

        UserEntity user = repository.findByUserName(userName).orElseThrow(() -> new NoSuchElementException("User is not registered"));
        ImageEntity img = new ImageEntity();
        try {
            img.setBytes(image.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Image loading error");
        }
        imgRepository.save(img);
        user.setImage(img);
        repository.save(user);
    }
}
