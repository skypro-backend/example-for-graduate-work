package ru.skypro.homework.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class UpdateUser {
    String firstName;
    String lastName;
    String phone;
}
