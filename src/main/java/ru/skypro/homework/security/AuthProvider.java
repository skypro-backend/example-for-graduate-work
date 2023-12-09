package ru.skypro.homework.security;

import org.springframework.security.core.Authentication;

public interface AuthProvider {
    Authentication getAuthentication();


    String getUsername();
}
