package ru.skypro.homework.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class User {
    private    int id;
    private  String email;
    private   String firsName;
    private   String lastName;
    private   String phone;
    private   String role;
    private  String image;
}
