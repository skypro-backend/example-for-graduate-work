package ru.skypro.homework.projection;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@RequiredArgsConstructor
@Data
public class UpdateUser {
    @Size(min = 3, max = 10)
    private final String firstName;
    @Size(min = 3, max = 10)
    private final String lastName;
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private final String phone;
}
