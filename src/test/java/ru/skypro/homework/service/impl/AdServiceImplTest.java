package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdServiceImplTest {

    @Mock
    private AdRepository adRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AdMapper adMapper;

    @InjectMocks
    private AdServiceImpl adService;

    private UserEntity user;
    private AdEntity ad;

    @BeforeEach
    void setUp() {
        user = new UserEntity();
        user.setEmail("user@test.com");
        user.setRole(Role.USER);

        ad = new AdEntity();
        ad.setId(1);
        ad.setAuthor(user);
    }

    @Test
    void shouldReturnTrueWhenUserIsAuthor() {
        when(adRepository.findById(1)).thenReturn(Optional.of(ad));

        boolean result = adService.isAuthorOrAdmin(1, "user@test.com");

        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenUserIsAdmin() {
        UserEntity admin = new UserEntity();
        admin.setEmail("admin@test.com");
        admin.setRole(Role.ADMIN);

        when(adRepository.findById(1)).thenReturn(Optional.of(ad));
        when(userRepository.findByEmail("admin@test.com")).thenReturn(Optional.of(admin));

        boolean result = adService.isAuthorOrAdmin(1, "admin@test.com");

        assertTrue(result);
    }

    @Test
    void getAd_shouldThrowExceptionWhenNotFound() {
        when(adRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> adService.getAd(99));
    }
}