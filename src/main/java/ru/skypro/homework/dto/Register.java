package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Register {

    public String getTitle;
    public String getImage;
    public int getPrice;
    private String username = "string";
    private String password = "stringst";
    private String firstName = "string";
    private String lastName = "string";
    private String phone = "string";
    @Enumerated(EnumType.STRING)
    private Role role = Role.valueOf("USER");

}

