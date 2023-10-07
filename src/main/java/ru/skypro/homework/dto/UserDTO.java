package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class UserDTO {

    Integer id;
    String email;
    String firstName;
    String lastName;
    String phone;
    String role;
    /**
     * {@link Role}
     */
    // ссылка на фото пользователя
    String image;
}

