package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.user.ChangePasswordRequest;
import ru.skypro.homework.dto.user.UpdateUserProfileRequest;
import ru.skypro.homework.entity.Register;
import ru.skypro.homework.entity.Role;
import ru.skypro.homework.dto.user.UpdateUserRequest;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public List<UserDTO> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            return users.stream().map(userMapper::userToUserDTO).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("An error occurred while getting all users: {}", e.getMessage());
            throw new RuntimeException("Failed to retrieve users.", e);
        }
    }

    public UserDTO getUserById(Long userId) {
        try {
            Optional<User> optionalUser = userRepository.findById(userId);
            User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));
            return userMapper.userToUserDTO(user);
        } catch (UsernameNotFoundException e) {
            logger.error("User not found with id: {}", userId);
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred while getting user by id {}: {}", userId, e.getMessage());
            throw new RuntimeException("Failed to retrieve user.", e);
        }
    }

    public UserDTO createUser(Register register) {
        try {
            if (userRepository.existsByUsername(register.getUsername())) {
                throw new IllegalArgumentException("Username is already taken: " + register.getUsername());
            }

            User user = new User();
            user.setUsername(register.getUsername());
            user.setPassword(passwordEncoder.encode(register.getPassword()));
            user.setFirstName(register.getFirstName());
            user.setLastName(register.getLastName());
            user.setPhone(register.getPhone());
            user.setRole(String.valueOf(Role.USER));

            user = userRepository.save(user);

            return userMapper.userToUserDTO(user);
        } catch (IllegalArgumentException e) {
            logger.error("Username is already taken: {}", register.getUsername());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred while creating a user: {}", e.getMessage());
            throw new RuntimeException("Failed to create user.", e);
        }
    }

    public UserDTO updateUser(Long userId, UpdateUserRequest updateUserRequest) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));

            user.setFirstName(updateUserRequest.getFirstName());
            user.setLastName(updateUserRequest.getLastName());
            user.setPhone(updateUserRequest.getPhone());

            user = userRepository.save(user);

            return userMapper.userToUserDTO(user);
        } catch (UsernameNotFoundException e) {
            logger.error("User not found with id: {}", userId);
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred while updating user with id {}: {}", userId, e.getMessage());
            throw new RuntimeException("Failed to update user.", e);
        }
    }

    public void deleteUser(Long userId) {
        try {
            userRepository.deleteById(userId);
        } catch (Exception e) {
            logger.error("An error occurred while deleting user with id {}: {}", userId, e.getMessage());
            throw new RuntimeException("Failed to delete user.", e);
        }
    }

    public UserDTO getUserProfile(Long userId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));
            return userMapper.userToUserDTO(user);
        } catch (UsernameNotFoundException e) {
            logger.error("User not found with id: {}", userId);
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred while getting user profile for id {}: {}", userId, e.getMessage());
            throw new RuntimeException("Failed to retrieve user profile.", e);
        }
    }
    public UserDTO updateUserProfile(Long userId, UpdateUserProfileRequest updateUserProfileRequest) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));

            // Обновление информации о пользователе на основе запроса updateUserProfileRequest

            user = userRepository.save(user);

            return userMapper.userToUserDTO(user);
        } catch (UsernameNotFoundException e) {
            logger.error("User not found with id: {}", userId);
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred while updating user profile for id {}: {}", userId, e.getMessage());
            throw new RuntimeException("Failed to update user profile.", e);
        }
    }
    public void changePassword(Long userId, ChangePasswordRequest changePasswordRequest) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));

            // Проверка, что текущий пароль совпадает с переданным в запросе
            if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), user.getPassword())) {
                throw new IllegalArgumentException("Current password is incorrect");
            }

            // Установка нового пароля
            user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
            userRepository.save(user);
        } catch (UsernameNotFoundException e) {
            logger.error("User not found with id: {}", userId);
            throw e;
        } catch (IllegalArgumentException e) {
            logger.error("Current password is incorrect for user with id {}: {}", userId, e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred while changing password for user with id {}: {}", userId, e.getMessage());
            throw new RuntimeException("Failed to change password.", e);
        }
    }
}