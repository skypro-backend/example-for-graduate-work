package ru.skypro.homework.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.skypro.homework.dto.User;
import org.springframework.stereotype.Service;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id)
                .flatMap(userMapper::toDto);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .flatMap(userMapper::toDto);
    }

    public Optional<User> updateUser(User userDto) {
        return userRepository.findById(userDto.getId())
                .map(entity -> {
                    entity.setFirstName(userDto.getFirstName());
                    entity.setLastName(userDto.getLastName());
                    entity.setPhone(userDto.getPhone());
                    entity.setImage(userDto.getImage());
                    return userRepository.save(entity);
                })
                .flatMap(userMapper::toDto);
    }

    public boolean changePassword(String email, String oldPassword, String newPassword) {
        return userRepository.findByEmail(email)
                .map(user -> {
                    if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
                        return false;
                    }
                    user.setPassword(passwordEncoder.encode(newPassword));
                    userRepository.save(user);
                    return true;
                })
                .orElse(false);
    }

    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

}
