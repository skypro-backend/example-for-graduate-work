package ru.skypro.homework.service.impl;


import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.enums.Role;
import ru.skypro.homework.dto.user.MyUserDetails;
import ru.skypro.homework.exception.notFound.LoginNotFoundException;
import ru.skypro.homework.exception.notFound.RegisterReqNotFoundException;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.MyUserDetailsManager;

@Service
public class AuthServiceImpl implements AuthService {

    private final MyUserDetailsManager manager;

    private final PasswordEncoder encoder;

    public AuthServiceImpl( MyUserDetailsManager manager, PasswordEncoder passwordEncoder ) {
        this.manager = manager;
        this.encoder = passwordEncoder;
    }

    @Override
    public boolean login( String username, String password ) {
        if (username == null || password == null) {
            throw new LoginNotFoundException();
        }

        if (!manager.userExists(username)) {
            return false;
        }
        MyUserDetails userDetails = manager.loadUserByUsername(username);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register( RegisterReq registerReq ) {
        if (registerReq == null) {
            throw new RegisterReqNotFoundException();
        }
        UserModel userModel = registerReq.toModel();
        userModel.setRole(Role.USER);
        if (manager.userExists(registerReq.getUsername())) {
            return false;
        }
        manager.createUser(MyUserDetails.fromModel(userModel));
        return true;
    }


}
