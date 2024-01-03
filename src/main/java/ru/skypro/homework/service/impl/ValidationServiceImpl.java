package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class ValidationServiceImpl {


        public static boolean isAdmin(Authentication authentication) {
            return authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList())
                    .contains("ROLE_ADMIN");
        }

        public static boolean isOwner(Authentication authentication, String userName) {
            return authentication.getName().equals(userName);
        }
    }

