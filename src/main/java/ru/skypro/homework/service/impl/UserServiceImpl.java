package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.config.SecurityUserDetailsService;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.exeptions.ForbiddenException;
import ru.skypro.homework.exeptions.UnauthorizedException;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.entities.ImageEntity;
import ru.skypro.homework.service.entities.UserEntity;
import ru.skypro.homework.service.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SecurityUserDetailsService securityUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final ImageServiceImpl imageService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           SecurityUserDetailsService securityUserDetailsService,
                           PasswordEncoder passwordEncoder, ImageServiceImpl imageService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.securityUserDetailsService = securityUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.imageService = imageService;
    }

    @Override
    public UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO) {
        UserEntity userEntity = getCurrentUser();
        UserEntity updateUserEntity = userMapper.userEntityUpdate(updateUserDTO, userEntity);
        UpdateUserDTO updateUser = userMapper.toUpdateUserDto(userEntity);
        userRepository.saveAndFlush(updateUserEntity);

        return updateUser;
    }

    @Override
    public UserDTO getUser() {
        UserEntity user = getCurrentUser();
        return userMapper.toUserDto(user);
    }

    @Override
    public void updatePassword(NewPasswordDTO newPasswordDTO) {
        String newPassword = newPasswordDTO.getNewPassword();

        if (newPassword.length() < 8) {
            throw new ForbiddenException("Новый пароль меньше 8 символов");
        }

        UserEntity user = getCurrentUser();
        UserDetails userDetails = securityUserDetailsService.loadUserByUsername(user.getEmail());

        if (!passwordEncoder.matches(newPasswordDTO.getCurrentPassword(), userDetails.getPassword())) {
            throw new UnauthorizedException("Пароли не совпадают");
        }

        String encodedNewPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedNewPassword);
        userRepository.saveAndFlush(user);
    }

    @Override
    public void updateAvatar(MultipartFile image) {
        UserEntity user = getCurrentUser();
        ImageEntity imageEntity = imageService.downloadImage(image);
        user.setImageEntity(imageEntity);
        userRepository.saveAndFlush(user);
    }

    /**
     * Получение инорфмации о текущем авторизованном пользователе
     */
    private UserEntity getCurrentUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(userName);
    }
}

