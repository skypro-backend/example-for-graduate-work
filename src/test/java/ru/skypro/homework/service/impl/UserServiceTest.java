package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.UserServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserDetailsManager manager;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UserServiceImpl userService;

    private UserEntity userEntity;
    private User userDto;
    private final String username = "sopetov@example.com";

    @BeforeEach
    void setUp() {
        userEntity = new UserEntity();
        userEntity.setEmail(username);
        userEntity.setFirstName("Антон");

        userDto = new User();
        userDto.setEmail(username);
        userDto.setFirstName("Антон");
    }

    @Test
    void shouldReturnUserWhenUsernameExists() {
        when(userRepository.findByEmail(username)).thenReturn(Optional.of(userEntity));
        when(userMapper.toDto(userEntity)).thenReturn(userDto);

        User result = userService.getUser(username);

        assertNotNull(result);
        assertEquals("Антон", result.getFirstName());
        verify(userRepository, times(1)).findByEmail(username);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        when(userRepository.findByEmail(username)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> userService.getUser(username));
    }
}