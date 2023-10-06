package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.MyDatabaseUserDetails;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UsersService;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public void setPassword(String currentPassword, String newPassword, String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (passwordEncoder.matches(currentPassword, userEntity.getPassword())) {
            userEntity.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(userEntity);
        } else {
            throw new BadCredentialsException("Неверный текущий пароль");
        }
    }

    @Override
    @Transactional
    public Optional<UserEntity> getUser(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        return userMapper.userEntityToUser(userEntity);
    }

    @Override
    @Transactional
    public UpdateUser updateUser(UpdateUser updateUser, String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        userEntity.setFirstName(updateUser.getFirstName());
        userEntity.setLastName(updateUser.getLastName());
        userEntity.setPhone(updateUser.getPhone());
        userRepository.save(userEntity);
        return userMapper.userEntityToUpdateUser(userEntity);
    }

    @Override
    @Transactional
    public void updateUserImage(MultipartFile file, String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        String filePath = "";
        userEntity.setImage(filePath);
        userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public void createUser(UserDetails myDatabaseUserDetails) {
        UserEntity userEntity = ((MyDatabaseUserDetails) myDatabaseUserDetails).getUserEntity();
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public boolean userExists(String username) {
        return userRepository.findByUsername(username) != null;
    }

    @Override
    public Optional<UpdateUser> updateUser(String name, UpdateUser userUpdate) {
        return Optional.empty();
    }
}
