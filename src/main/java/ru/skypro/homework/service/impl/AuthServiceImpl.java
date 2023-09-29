package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.MyDatabaseUserDetails;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.transformer.UserTransformer;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MyDatabaseUserDetailsService myDatabaseUserDetailsService;
    private final PasswordEncoder encoder;
    private final UserTransformer userTransformer;

    @Override
    public boolean login(String userName, String password) {
        if (!myDatabaseUserDetailsService.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = myDatabaseUserDetailsService.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(Register register) {
        UserEntity userEntity = userTransformer.fromRegisterToUserEntity(register);
        MyDatabaseUserDetails myDatabaseUserDetails = new MyDatabaseUserDetails(userEntity);
        if (! myDatabaseUserDetailsService.userExists(register.getUsername())) {
            myDatabaseUserDetailsService.createUser(myDatabaseUserDetails);
            return true;
        } else {
            return false;
        }
    }
}
