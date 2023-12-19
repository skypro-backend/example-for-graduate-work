package ru.skypro.homework.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class UserDTO {
    private Integer id;

    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private Role role;

    private String image;


}

