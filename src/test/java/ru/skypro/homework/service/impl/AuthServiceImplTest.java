package ru.skypro.homework.service.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.exception.IncorrectPasswordException;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.Role;
import ru.skypro.homework.repository.UserRepository;

@ContextConfiguration(classes = {AuthServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AuthServiceImplTest {
    @Autowired
    private AuthServiceImpl authServiceImpl;

    @MockBean
    private MyUserDetailsService myUserDetailsService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test:  {@link AuthServiceImpl#login(String, String)}
     */
    @Test
    void testLogin() throws UsernameNotFoundException {
        when(myUserDetailsService.loadUserByUsername(Mockito.<String>any()))
                .thenReturn(new User("janedoe", "iloveyou", new ArrayList<>()));
        when(passwordEncoder.matches(Mockito.<CharSequence>any(), Mockito.<String>any())).thenReturn(true);
        boolean actualLoginResult = authServiceImpl.login("janedoe", "iloveyou");
        verify(passwordEncoder).matches(Mockito.<CharSequence>any(), Mockito.<String>any());
        verify(myUserDetailsService).loadUserByUsername(Mockito.<String>any());
        assertTrue(actualLoginResult);
    }

    /**
     * Method under test:  {@link AuthServiceImpl#login(String, String)}
     */
    @Test
    void testLogin2() throws UsernameNotFoundException {
        when(myUserDetailsService.loadUserByUsername(Mockito.<String>any()))
                .thenReturn(new User("janedoe", "iloveyou", new ArrayList<>()));
        when(passwordEncoder.matches(Mockito.<CharSequence>any(), Mockito.<String>any()))
                .thenThrow(new IncorrectPasswordException("An error occurred"));
        assertThrows(IncorrectPasswordException.class, () -> authServiceImpl.login("janedoe", "iloveyou"));
        verify(passwordEncoder).matches(Mockito.<CharSequence>any(), Mockito.<String>any());
        verify(myUserDetailsService).loadUserByUsername(Mockito.<String>any());
    }

    /**
     * Method under test:  {@link AuthServiceImpl#login(String, String)}
     */
    @Test
    void testLogin3() throws UsernameNotFoundException {
        when(myUserDetailsService.loadUserByUsername(Mockito.<String>any()))
                .thenReturn(new User("janedoe", "iloveyou", new ArrayList<>()));
        when(passwordEncoder.matches(Mockito.<CharSequence>any(), Mockito.<String>any())).thenReturn(false);
        assertThrows(IncorrectPasswordException.class, () -> authServiceImpl.login("janedoe", "iloveyou"));
        verify(passwordEncoder).matches(Mockito.<CharSequence>any(), Mockito.<String>any());
        verify(myUserDetailsService).loadUserByUsername(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AuthServiceImpl#register(RegisterDTO)}
     */
    @Test
    void testRegister() throws UnsupportedEncodingException {
        Image image = new Image();
        image.setData("AXAXAXAX".getBytes("UTF-8"));
        image.setFileSize(3L);
        image.setId(1L);
        image.setMediaType("Media Type");

        ru.skypro.homework.model.User user = new ru.skypro.homework.model.User();
        user.setAds(new ArrayList<>());
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1L);
        user.setImage(image);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRole(Role.USER);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(user);
        boolean actualRegisterResult = authServiceImpl.register(new RegisterDTO());
        verify(userRepository).findByEmail(Mockito.<String>any());
        assertFalse(actualRegisterResult);
    }
}
