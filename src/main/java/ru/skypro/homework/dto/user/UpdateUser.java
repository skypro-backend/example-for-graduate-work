package ru.skypro.homework.dto.user;

import lombok.Data;
import javax.validation.constraints.Pattern;


@Data
public class UpdateUser {
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    private String firstName;

    private String lastName;



}
