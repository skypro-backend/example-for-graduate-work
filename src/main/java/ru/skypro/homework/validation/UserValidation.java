package ru.skypro.homework.validation;

import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateUser;


import java.util.regex.Pattern;

public class UserValidation {

    public static boolean validateNewPassword(NewPassword newPassword) {
        return validateStringParam(newPassword.getNewPassword(), 16,8)
                & validateStringParam(newPassword.getCurrentPassword(), 16,8);
    }

    public static boolean validateUpdateUser(UpdateUser updateUser) {
        return validateStringParam(updateUser.getFirstName(), 10, 3)
                & validateStringParam(updateUser.getLastName(), 10, 3)
                & validatePhone(updateUser.getPhone());
    }

    public static boolean validateRegister(Register register) {
        return validateStringParam(register.getUsername(), 32, 4) &
                validateStringParam(register.getPassword(), 16, 8) &
                validateStringParam(register.getFirstName(), 16, 2) &
                validateStringParam(register.getLastName(), 16, 2) &
                validatePhone(register.getPhone());
    }

    public static boolean validateStringParam(String name, int max, int min) {
        return (name.length() <= max) & (name.length() >= min);
    }

    public static boolean validatePhone(String phone) {
        return Pattern.matches("\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}", phone);
    }

    public static boolean validateLogin(Login login) {
        return validateStringParam(login.getUsername(), 32, 4)
                & validateStringParam(login.getPassword(), 16, 8);
    }
}


