package ru.skypro.homework.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.skypro.homework.entity.User;

@Data
@RequiredArgsConstructor
public class AdDto {
    int pk;
    User user;
    String title;
    byte[] image;
    int price;
    String description;

}
