package ru.skypro.homework.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.entity.Users;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUser {
    @Size(message = "введите от 3 до 10 символов", min = 3, max = 10)
    private String firstName;
    @Size(message = "введите от 3 до 10 символов", min = 3, max = 10)
    private String lastName;
    @Pattern(message = "введите номер телефона согласно шаблона +7(777)777-77-77", regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    public static UpdateUser toUpdateUser(Users users) {
        UpdateUser updateUser = new UpdateUser();
        updateUser.setPhone(users.getPhone());
        updateUser.setFirstName(users.getFirstName());
        updateUser.setLastName(users.getLastName());
        return updateUser;
    }
}