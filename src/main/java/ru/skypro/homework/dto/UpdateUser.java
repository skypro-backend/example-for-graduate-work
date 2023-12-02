package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUser {

    private String firstName = "string";
    private String lastName = "string";
    private String phone = "+7(300) 0524971";

}
