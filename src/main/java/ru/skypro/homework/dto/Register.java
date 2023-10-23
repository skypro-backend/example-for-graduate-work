package ru.skypro.homework.dto;

import lombok.*;
import org.springframework.http.ResponseEntity;
import ru.skypro.homework.repository.UserRepository;

import java.util.regex.Pattern;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Register {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;

    private static UserRepository userRepository;

    public Register(String username, String password, String firstName, String lastName, String phone, Role role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.role = role;
    }

    public static boolean validateRegister(Register register) {

        Pattern pattern = Pattern.compile("\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}");
        Boolean matcher = pattern.matcher(register.getPhone()).matches();

        if ((register.getUsername().length() < 4 || register.getUsername().length() > 32) || (register.getPassword().length() < 8 || register.getPassword().length() > 16) || (register.getFirstName().length() < 2 || register.getFirstName().length() > 16) || (register.getLastName().length() < 2 || register.getLastName().length() > 16) || (matcher)) {
            return false;
        } else {
            return true;
        }
    }
}
