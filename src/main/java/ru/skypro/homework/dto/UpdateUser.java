package ru.skypro.homework.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUser {
    private String firstName;
    private String lastName;
    private String phone;
}
