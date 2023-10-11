package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.pojo.Image;

@Data
public class AdInfoDTO {
    private Long pk;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String phone;
    private Long price;
    private String title;
    private Image image;
}
