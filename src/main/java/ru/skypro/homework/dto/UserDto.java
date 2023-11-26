package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.model.Image;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer id = 0;
    private String username = "string";
    private String firstName = "string";
    private String lastName = "string";
    private Integer phone = 0;
    private Role role = Role.USER;
    private String image = "string";

    public UserDto(Integer id, String username, String firstName, String lastName, Integer phone, Role role, Image image) {

    }
}
