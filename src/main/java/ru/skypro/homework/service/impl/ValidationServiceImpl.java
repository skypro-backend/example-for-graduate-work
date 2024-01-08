package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class ValidationServiceImpl {


        public static boolean isAdmin (Authentication authentication, String roleAutorAd) {
                boolean isAdmin = authentication.getAuthorities()
                        .stream()
                        .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

                boolean isOwnerAd = authentication.getName().equals(roleAutorAd);

                return isAdmin || isOwnerAd;
            }
        }

