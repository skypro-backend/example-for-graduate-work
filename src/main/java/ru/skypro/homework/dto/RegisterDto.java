package ru.skypro.homework.dto;

import lombok.*;
import ru.skypro.homework.repository.UserRepository;

import java.util.regex.Pattern;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class RegisterDto {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;

    private static UserRepository userRepository;

    public RegisterDto(String username, String password, String firstName, String lastName, String phone, Role role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.role = role;
    }

    public static boolean validateRegister(RegisterDto register) {

        Pattern pattern = Pattern.compile("\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}");
        boolean matcher = pattern.matcher(register.getPhone()).matches();

        return (register.getUsername().length() >= 4 && register.getUsername().length() <= 32)
               && (register.getPassword().length() >= 8 && register.getPassword().length() <= 16)
               && (register.getFirstName().length() >= 2 && register.getFirstName().length() <= 16)
               && (register.getLastName().length() >= 2 && register.getLastName().length() <= 16)
               && (!matcher);
    }
}
