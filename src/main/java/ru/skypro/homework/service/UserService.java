package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public boolean userExists(String username) {
        return userRepository.existsUserByUsername(username);
    }

    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
