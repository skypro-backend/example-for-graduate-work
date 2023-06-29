package ru.skypro.homework.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {

    private Integer id;
    private String email; //логин
    private String firstName;
    private String lastName;
    private String phone;
    private String image; //ссылка на аватар пользователя
}
