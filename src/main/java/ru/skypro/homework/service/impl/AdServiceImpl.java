package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.service.AdService;
@Service
public class AdServiceImpl implements AdService {
    @Override
    public boolean login(String userName, String password) {
        return false;
    }

    @Override
    public boolean register(RegisterDto registerDto) {
        return false;
    }
}
