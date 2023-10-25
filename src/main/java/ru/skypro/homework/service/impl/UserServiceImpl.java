package ru.skypro.homework.service.impl;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateUser;
import ru.skypro.homework.dto.user.User;
import ru.skypro.homework.entity.Users;
import ru.skypro.homework.exceptions.UserNotFoundException;
import ru.skypro.homework.exceptions.WrongCurrentPasswordException;
import ru.skypro.homework.repository.UsersRepository;
import ru.skypro.homework.service.UserService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void updatePassword(NewPassword newPassword, String username) {
        Users users = usersRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        String currentPassword = passwordEncoder.encode(newPassword.getCurrentPassword());
        if (currentPassword.equals(users.getPassword())) {
            users.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
            usersRepository.save(users);
        } else {
            throw new WrongCurrentPasswordException();
        }
    }

    @Override
    public User getInformation(String username) {
        Users users = usersRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return User.toUser(users);
    }

    @Override
    public UpdateUser updateInformationAboutUser(UpdateUser updateUser, String username) {
        Users users = usersRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        users.setFirstName(updateUser.getFirstName());
        users.setLastName(updateUser.getLastName());
        users.setPhone(updateUser.getPhone());
        usersRepository.save(users);
        return UpdateUser.toUpdateUser(users);
    }

    @Override
    public void UpdateImage(MultipartFile file, String username) throws IOException {
        Users users = usersRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        File files = file.getResource().getFile();
        String filePath = files.getPath();
        users.setImage(filePath);
        usersRepository.save(users);
    }
}