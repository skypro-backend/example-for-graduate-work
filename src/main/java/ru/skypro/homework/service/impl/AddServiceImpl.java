package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.service.AddService;
@Service
public class AddServiceImpl implements AddService {
    @Override
    public boolean login(String userName, String password) {
        return false;
    }

    @Override
    public boolean register(Register register) {
        return false;
    }
}
