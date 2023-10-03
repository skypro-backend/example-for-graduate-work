package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.MyDatabaseUserDetails;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UsersService;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public void setPassword(String currentPassword, String newPassword) {
        UserEntity userEntity = getCurrentUser();
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
        UserEntity userEntity = getCurrentUser();
        return userMapper.userEntityToUser(userEntity);
    }

    @Override
    @Transactional
    public UpdateUser updateUser(UpdateUser updateUser) {
        UserEntity userEntity = getCurrentUser();
        userEntity.setFirstName(updateUser.getFirstName());
        userEntity.setLastName(updateUser.getLastName());
        userEntity.setPhone(updateUser.getPhone());
        userRepository.save(userEntity);
        return userMapper.userEntityToUpdateUser(userEntity);
    }

    @Override
    @Transactional
    public void updateUserImage(MultipartFile file) {
        UserEntity userEntity = getCurrentUser();
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

    public UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyDatabaseUserDetails myDatabaseUserDetails = (MyDatabaseUserDetails) userDetailsService.loadUserByUsername(authentication.getName());
        return myDatabaseUserDetails.toUserEntity();
    }
}
