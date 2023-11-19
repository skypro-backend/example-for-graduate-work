package ru.skypro.homework.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateUser {
    private String firstName;
    private String lastName;
    private String phone;
}
