package ru.skypro.sitesforresaleofthings.service;

import ru.skypro.sitesforresaleofthings.dto.Register;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(Register register);
}
