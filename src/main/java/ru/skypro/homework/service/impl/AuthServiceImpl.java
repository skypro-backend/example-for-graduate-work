package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.exception.IncorrectArgumentException;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PersonalizedUserInformationServiceImpl manager;
    private final PasswordEncoder encoder;


    @Override
    public boolean login(String userName, String password) {
        log.debug("Logging in user: {}", userName);
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(Register register) {
//
//        if (userRepository.existsByUserNameIgnoreCase(register.getUsername())) {
//            return false;
//        }
//        ru.skypro.homework.entity.User user = new ru.skypro.homework.entity.User();
//        user.setUserName(register.getUsername());
//        user.setFirstName(register.getFirstName());
//        user.setLastName(register.getLastName());
//        user.setPassword(encoder.encode(register.getPassword()));
//        user.setRole(register.getRole());
//        user.setPhone(register.getPhone());
//        userRepository.save(user);
//        return true;
//    }
        if(register.getUsername() == null || register.getUsername().isBlank()
                || register.getFirstName() == null || register.getFirstName().isBlank()
                || register.getLastName() == null || register.getLastName().isBlank()
                || register.getPhone() == null || register.getPhone().isBlank()
                || register.getPassword() == null || register.getPassword().isBlank()) throw new IncorrectArgumentException();

        log.info("Registering new user: {}", register.getUsername());
        manager.createUser(register);
        log.info("User {} registered successfully", register.getUsername());
        return true;
    }

}
