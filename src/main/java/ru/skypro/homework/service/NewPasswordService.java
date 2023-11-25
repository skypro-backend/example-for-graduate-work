package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPasswordDto;

public interface NewPasswordService {
    NewPasswordDto setPassword(Integer id,
                               NewPasswordDto newPasswordDto);
}
