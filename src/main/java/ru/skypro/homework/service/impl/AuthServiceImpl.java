package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.MyDatabaseUserDetails;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UsersService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder encoder;
    private final UsersService userService;
    private final UserMapper userMapper;

    @Override
    public boolean login(String userName, String password) {
        if (!userService.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(Register register) {
        UserEntity userEntity = userMapper.registerToUserEntity(register);
        MyDatabaseUserDetails myDatabaseUserDetails =
                new MyDatabaseUserDetails(userMapper.userEntityToMyDatabaseUser(userEntity));
        if (! userService.userExists(register.getUsername())) {
            userService.createUser(myDatabaseUserDetails);
            return true;
        } else {
            return false;
        }
    }
}
