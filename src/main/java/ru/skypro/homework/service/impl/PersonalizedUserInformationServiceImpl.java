package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.PersonalizedUserInformation;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.IncorrectUsernameException;
import ru.skypro.homework.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonalizedUserInformationServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUserNameIgnoreCase(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new PersonalizedUserInformation(user);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public void createUser(Register registerReq) {
        if (userRepository.findByUserNameIgnoreCase(registerReq.getUserName()).isPresent()) {
            throw new IncorrectUsernameException();
        }

        User user = new User();
        user.setUserName(registerReq.getUserName());
        user.setPassword(passwordEncoder.encode(registerReq.getPassword()));
        user.setRole(Role.USER);
        user.setFirstName(registerReq.getFirstName());
        user.setLastName(registerReq.getLastName());
        user.setPhone(registerReq.getPhone());
        user.setEnable(true);
        userRepository.save(user);
    }

}
