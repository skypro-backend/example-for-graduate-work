package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.models.Image;
import ru.skypro.homework.models.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.AuthProvider;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final AuthProvider authProvider;
    private final AuthService authService;
    private final UserMapper mapper;
    private final ImageService imageService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto getUser() {
        return mapper.convertToDto(repository.findAuthUserByEmail(authProvider.getUsername()));
    }

    @Transactional
    @Override
    public UpdateUserDto updateUser(UpdateUserDto updateUser) {
        User user = repository.findAuthUserByEmail(authProvider.getUsername());

        if (updateUser.getFirstName() != null) {
            user.setFirstName(updateUser.getFirstName());
        }
        if (updateUser.getLastName() != null) {
            user.setLastName(updateUser.getLastName());
        }
        if (updateUser.getPhone() != null) {
            user.setPhone(updateUser.getPhone());
        }

        return updateUser;
    }

    @Transactional
    @Override
    public void updateUserImage(MultipartFile file) {
        User user = repository.findAuthUserByEmail(authProvider.getUsername());
        if (user.getImage() == null) {
            user.setImage(new Image());
        }
        imageService.update(user.getImage(), file);
        repository.save(user);
    }

    @Transactional
    @Override
    public boolean setPassword(NewPasswordDto newPassword) {
        User user = repository.findAuthUserByEmail(authProvider.getUsername());
        if (authService.checkPasswords(newPassword.getCurrentPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
            repository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public byte[] getImage(Integer id) {
        return repository.findById(id)
                .flatMap(user -> Optional.ofNullable(user.getImage()))
                .map(image -> imageService.download(image.getImagePath()))
                .orElse(null);
    }

}
