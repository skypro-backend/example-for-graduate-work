package ru.skypro.homework.service.auth;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.auth.LoginDto;
import ru.skypro.homework.dto.auth.RegisterDto;

public interface AuthService {
    boolean register(RegisterDto registerDto);

    boolean login(LoginDto loginDto);

    boolean isUserAllowedToChangeAds(Authentication authentication, Integer adId);

    boolean isUserAllowedToChangeComments(Authentication authentication, Integer adId, Integer commentId);
}
