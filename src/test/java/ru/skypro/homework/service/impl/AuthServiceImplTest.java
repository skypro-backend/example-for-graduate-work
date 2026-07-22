package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.Role;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthServiceImplTest {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserMapper userMapper;

    private AuthServiceImpl authService;


    @BeforeEach
    void setUp() {

        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userMapper = mock(UserMapper.class);

        authService = new AuthServiceImpl(
                userRepository,
                passwordEncoder,
                userMapper
        );
    }


    @Test
    void shouldRegisterUserSuccessfully() {

        Register register = new Register();

        register.setUsername("test@mail.ru");
        register.setPassword("12345");


        User user = new User();

        user.setEmail("test@mail.ru");


        when(userRepository.findByEmail(
                "test@mail.ru"
        ))
                .thenReturn(Optional.empty());


        when(userMapper.registerToEntity(register))
                .thenReturn(user);


        when(passwordEncoder.encode("12345"))
                .thenReturn("encodedPassword");


        boolean result =
                authService.register(register);


        assertThat(result)
                .isTrue();


        assertThat(user.getPassword())
                .isEqualTo("encodedPassword");


        assertThat(user.getRole())
                .isEqualTo(Role.USER);


        verify(userRepository)
                .save(user);
    }



    @Test
    void shouldNotRegisterExistingUser() {

        Register register = new Register();

        register.setUsername("test@mail.ru");


        User existingUser = new User();


        when(userRepository.findByEmail(
                "test@mail.ru"
        ))
                .thenReturn(
                        Optional.of(existingUser)
                );


        boolean result =
                authService.register(register);


        assertThat(result)
                .isFalse();


        verify(userRepository, never())
                .save(any());
    }



    @Test
    void shouldLoginSuccessfully() {

        User user = new User();

        user.setEmail("test@mail.ru");
        user.setPassword("encodedPassword");


        when(userRepository.findByEmail(
                "test@mail.ru"
        ))
                .thenReturn(
                        Optional.of(user)
                );


        when(passwordEncoder.matches(
                "12345",
                "encodedPassword"
        ))
                .thenReturn(true);


        boolean result =
                authService.login(
                        "test@mail.ru",
                        "12345"
                );


        assertThat(result)
                .isTrue();
    }



    @Test
    void shouldReturnFalseWhenPasswordWrong() {

        User user = new User();

        user.setEmail("test@mail.ru");
        user.setPassword("encodedPassword");


        when(userRepository.findByEmail(
                "test@mail.ru"
        ))
                .thenReturn(
                        Optional.of(user)
                );


        when(passwordEncoder.matches(
                "wrong",
                "encodedPassword"
        ))
                .thenReturn(false);


        boolean result =
                authService.login(
                        "test@mail.ru",
                        "wrong"
                );


        assertThat(result)
                .isFalse();
    }
}