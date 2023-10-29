package ru.skypro.homework.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UserDetailsDTO;
import ru.skypro.homework.pojo.User;
import ru.skypro.homework.repository.UserRepository;

import java.util.*;

@Service

public class UserService implements UserDetailsService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<User> userOptional = Optional.ofNullable(userRepository.findUserByUserName(userName));
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + userName));

        // Преобразование сущности User в объект UserDetailsDTO
        UserDetailsDTO userDetails = new UserDetailsDTO(
                user.getUserID(),
                user.getEmail(),
                user.getUserName(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getRole(),
                user.getImage(),
                user.getPassword()
        );

        return userDetails;
    }

    // Преобразование списка ролей в коллекцию GrantedAuthority
    private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_"+role.name()));
    }

}