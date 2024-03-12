package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class UserDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    protected Integer id;
    @Email
    protected String email;
    protected String firstName;
    protected String lastName;
    protected String phone;
    protected String image;
}
