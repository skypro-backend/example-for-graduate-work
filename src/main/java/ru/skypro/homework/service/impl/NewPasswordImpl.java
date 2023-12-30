package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import ru.skypro.homework.service.NewPasswordService;

@Service
public class NewPasswordImpl implements NewPasswordService {
    private final Logger logger = LoggerFactory.getLogger(NewPasswordImpl.class);
    @Override
    public void setPassword(String currentPassword,  String newPassword) {

    }
}
