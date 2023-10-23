package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UserDetailsDTO;
import ru.skypro.homework.pojo.User;
import ru.skypro.homework.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.file.attribute.UserPrincipal;
import java.util.Optional;

@Service

public class UserServiceImpl implements UserService, UserDetailsService {


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(userName);
        return new UserDetailsDTO(
                user.getUserID(),
                user.getRole(),
                user.getUserName(),
                user.getPassword());
    }

}
