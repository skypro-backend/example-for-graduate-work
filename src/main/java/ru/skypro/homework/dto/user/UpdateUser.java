package ru.skypro.homework.dto.user;

import lombok.Data;
import javax.validation.constraints.Pattern;


@Data
public class UpdateUser {

    private String firstName;
    private String lastName;
    private String phone;



}
