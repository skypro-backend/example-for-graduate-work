package ru.skypro.homework.util;

import org.springframework.security.core.context.SecurityContextHolder;
import ru.skypro.homework.security.CustomUserDetails;

public class SecurityUtil {
    public SecurityUtil() {
    }

    public static CustomUserDetails getUserDetails() {
        return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
