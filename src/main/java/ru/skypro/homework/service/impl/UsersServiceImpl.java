package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UsersService;
import ru.skypro.homework.transformer.UserTransformer;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MyDatabaseUserDetailsService myDatabaseUserDetailsService;
    private final UserTransformer userTransformer;

    @Override
    @Transactional
    public void setPassword(String currentPassword, String newPassword) {
        UserEntity userEntity = myDatabaseUserDetailsService.getCurrentUser();
        if (passwordEncoder.matches(currentPassword, userEntity.getPassword())) {
            userEntity.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(userEntity);
        } else {
            throw new BadCredentialsException("Пароль не соответствует текущему");
        }
    }

    @Override
    @Transactional
    public User getUser() {
        UserEntity userEntity = myDatabaseUserDetailsService.getCurrentUser();
        return userTransformer.userEntitytoUser(userEntity);
    }

    @Override
    @Transactional
    public UpdateUser updateUser(UpdateUser updateUser) {
        UserEntity userEntity = myDatabaseUserDetailsService.getCurrentUser();
        userEntity.setFirstName(updateUser.getFirstName());
        userEntity.setLastName(updateUser.getLastName());
        userEntity.setPhone(updateUser.getPhone());
        userRepository.save(userEntity);
        return userTransformer.userEntityToUpdateUser(userEntity);
    }

    @Override
    @Transactional
    public void updateUserImage(MultipartFile file) {
        UserEntity userEntity = myDatabaseUserDetailsService.getCurrentUser();
        String filePath = "";
        userEntity.setImage(filePath);
        userRepository.save(userEntity);
    }
}
