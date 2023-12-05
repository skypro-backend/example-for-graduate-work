package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User get(Integer id) {
        logger.info("Method get was invoked!");
        return userRepository.findById(id)
                .orElse(null);
    }
    @Override
    public UpdateUser updateUser(Integer id, UpdateUser updateUserDto) {
        logger.info("Method add update invoked!");
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(updateUserDtoFromDB -> {
            updateUserDtoFromDB.setFirstName(updateUserDto.getFirstName());
            updateUserDtoFromDB.setLastName(updateUserDto.getLastName());
            updateUserDtoFromDB.setPhone(updateUserDto.getPhone());
            userRepository.save(updateUserDtoFromDB);
        });
        return updateUserDto;
    }
    @Override
    public NewPassword setPassword(Integer id, NewPassword newPassword) {
        logger.info("Method add setPassword invoked!");
        Optional<User> newPasswordOptional = userRepository.findById(id);
        newPasswordOptional.ifPresent(setPasswordFromDB -> {
            setPasswordFromDB.setPassword(newPassword.getCurrentPassword());
            userRepository.save(setPasswordFromDB);
        });
        return newPassword;
    }
}