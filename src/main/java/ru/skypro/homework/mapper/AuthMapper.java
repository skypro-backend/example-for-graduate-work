package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.RegisterDTO;

public interface AuthMapper {
    boolean login(String userName, String password);

    boolean register(RegisterDTO register);
}
