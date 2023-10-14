package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.config.SecurityUserDetailsService;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.exeptions.ForbiddenException;
import ru.skypro.homework.exeptions.UnauthorizedException;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.entities.UserEntity;
import ru.skypro.homework.service.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SecurityUserDetailsService securityUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           SecurityUserDetailsService securityUserDetailsService,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.securityUserDetailsService = securityUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO) {
        UserEntity user = getCurrentUser();
        user.setFirstName(updateUserDTO.getFirstName());
        user.setLastName(updateUserDTO.getLastName());
        user.setPhone(updateUserDTO.getPhone());

        userRepository.saveAndFlush(user);

        return updateUserDTO;
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
        UserDetails userDetails = securityUserDetailsService.loadUserByUsername(user.getUsername());

        if (!passwordEncoder.matches(newPasswordDTO.getCurrentPassword(), userDetails.getPassword())) {
            throw new UnauthorizedException("Пароли не совпадают");
        }

        String encodedNewPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedNewPassword);
        userRepository.save(user);
    }

    private UserEntity getCurrentUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(userName);
        return userRepository.findByEmail(userName);
    }
}