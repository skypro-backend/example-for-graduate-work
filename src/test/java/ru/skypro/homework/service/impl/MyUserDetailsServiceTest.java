package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.skypro.homework.model.*;
import ru.skypro.homework.repository.UserRepository;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {MyUserDetailsService.class})
@ExtendWith(SpringExtension.class)
class MyUserDetailsServiceTest {
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link MyUserDetailsService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername() throws UnsupportedEncodingException, UsernameNotFoundException {
        Image image = new Image();
        image.setData("AXAXAXAX".getBytes("UTF-8"));
        image.setFileSize(3L);
        image.setId(1L);
        image.setMediaType("Media Type");

        User user = new User();
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
        UserDetails actualLoadUserByUsernameResult = myUserDetailsService.loadUserByUsername("janedoe");
        verify(userRepository).findByEmail(Mockito.<String>any());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
    }

    /**
     * Method under test: {@link MyUserDetailsService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername2() throws UnsupportedEncodingException, UsernameNotFoundException {
        Image image = new Image();
        image.setData("AXAXAXAX".getBytes("UTF-8"));
        image.setFileSize(3L);
        image.setId(1L);
        image.setMediaType("Media Type");
        User user = mock(User.class);
        when(user.getRole()).thenThrow(new UsernameNotFoundException("Msg"));
        when(user.getPassword()).thenReturn("iloveyou");
        doNothing().when(user).setAds(Mockito.<List<Ad>>any());
        doNothing().when(user).setComments(Mockito.<List<Comment>>any());
        doNothing().when(user).setEmail(Mockito.<String>any());
        doNothing().when(user).setFirstName(Mockito.<String>any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setImage(Mockito.<Image>any());
        doNothing().when(user).setLastName(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setPhone(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<Role>any());
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
        assertThrows(UsernameNotFoundException.class, () -> myUserDetailsService.loadUserByUsername("janedoe"));
        verify(user).getPassword();
        verify(user).getRole();
        verify(user).setAds(Mockito.<List<Ad>>any());
        verify(user).setComments(Mockito.<List<Comment>>any());
        verify(user).setEmail(Mockito.<String>any());
        verify(user).setFirstName(Mockito.<String>any());
        verify(user).setId(Mockito.<Long>any());
        verify(user).setImage(Mockito.<Image>any());
        verify(user).setLastName(Mockito.<String>any());
        verify(user).setPassword(Mockito.<String>any());
        verify(user).setPhone(Mockito.<String>any());
        verify(user).setRole(Mockito.<Role>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }
}
