package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.service.NewPasswordService;

import java.beans.Encoder;


@Service
public class NewPasswordImpl implements NewPasswordService {
    private final Logger logger = LoggerFactory.getLogger(NewPasswordImpl.class);

    private Encoder encoder;
    @Override
    public void setPassword(String currentPassword,  String newPassword) {
        logger.info("Current password: " + currentPassword +
                "| New password: " + newPassword);

    }
}
