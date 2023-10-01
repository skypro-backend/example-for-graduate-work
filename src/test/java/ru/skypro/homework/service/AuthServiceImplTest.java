package ru.skypro.homework.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.service.impl.AuthServiceImpl;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {
    @Mock
    private UserDetailsManager manager;
    @Mock
    private UserDetails userDetails;
    @Mock
    private PasswordEncoder encoder;


    @InjectMocks
    private AuthServiceImpl out;

    User user = new User(1, "Bob", "Mjn", "name", "password", "2332343212", "USER", true, "gdhfhfhdh");


    @Test
    void login() {
        String p = user.getPassword();
        String encPassword = user.getPassword()+"encoded";
        when(!manager.userExists(user.getUsername())).thenReturn(true);
        when(manager.loadUserByUsername(user.getUsername())).thenReturn(userDetails);
        when(encoder.matches(user.getPassword(), userDetails.getPassword()))
                .thenReturn(matches(p, encPassword));

        assertTrue(out.login(user.getUsername(), user.getPassword()));
    }

    private boolean matches(String password, String encodedPassword) {
        return Objects.equals(encodedPassword, password + "encoded");
    }


}
