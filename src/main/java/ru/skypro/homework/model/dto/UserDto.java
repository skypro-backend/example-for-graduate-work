package ru.skypro.homework.model.dto;

import lombok.Data;

@Data
public class UserDto {
    public Integer id;
    public String firstName;
    public String lastName;
    public String email;
    public String phone;
    private String regDate;
    private String city;
    private String avatar;
}
