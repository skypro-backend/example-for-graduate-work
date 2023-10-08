package ru.skypro.homework.dto;
import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    /**
     * {@link Role}
     */
    private String role;
    // ссылка на фото пользователя
    private String image;
}
