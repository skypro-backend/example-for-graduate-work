package ru.skypro.homework.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Encoder {
    public static PasswordEncoder passwordEncoderWithBCrypt() {
        return new BCryptPasswordEncoder();
    }
}