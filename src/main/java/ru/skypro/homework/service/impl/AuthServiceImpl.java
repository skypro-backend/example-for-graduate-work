package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.projections.Register;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserServiceSecurity;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserServiceSecurity manager;
    private final PasswordEncoder encoder;

    @Override
    public boolean login(String userName, String password) {
        if (manager.loadUserByUsername(userName) == null) {
            return false;
        }

        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(Register register) {
        if (register.getUsername() == null || register.getUsername().isBlank()
                || register.getFirstName() == null || register.getFirstName().isBlank()
                || register.getLastName() == null || register.getLastName().isBlank()
                || register.getPhone() == null || register.getPhone().isBlank()
                || register.getPassword() == null || register.getPassword().isBlank()){
            return false;
        }

        return true;
    }

}
