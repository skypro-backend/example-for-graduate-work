package ru.skypro.homework.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.skypro.homework.model.Role;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;

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
    void testLoadUserByUsername() throws UsernameNotFoundException {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1L);
        user.setImage("Image");
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
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        User user = mock(User.class);
        when(user.getRole()).thenThrow(new UsernameNotFoundException("Msg"));
        when(user.getPassword()).thenReturn("iloveyou");
        doNothing().when(user).setEmail(Mockito.<String>any());
        doNothing().when(user).setFirstName(Mockito.<String>any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setImage(Mockito.<String>any());
        doNothing().when(user).setLastName(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setPhone(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<Role>any());
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1L);
        user.setImage("Image");
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRole(Role.USER);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(user);
        assertThrows(UsernameNotFoundException.class, () -> myUserDetailsService.loadUserByUsername("janedoe"));
        verify(user).getPassword();
        verify(user).getRole();
        verify(user).setEmail(Mockito.<String>any());
        verify(user).setFirstName(Mockito.<String>any());
        verify(user).setId(Mockito.<Long>any());
        verify(user).setImage(Mockito.<String>any());
        verify(user).setLastName(Mockito.<String>any());
        verify(user).setPassword(Mockito.<String>any());
        verify(user).setPhone(Mockito.<String>any());
        verify(user).setRole(Mockito.<Role>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }
}
