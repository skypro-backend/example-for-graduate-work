package ru.skypro.homework.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class User {
    int id;
    String email;
    String firsName;
    String lastName;
    String phone;
    String role;
    String image;
}
