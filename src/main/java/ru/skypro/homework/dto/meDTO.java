package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.pojo.Image;
@Data
public class meDTO {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    private String image;


}
