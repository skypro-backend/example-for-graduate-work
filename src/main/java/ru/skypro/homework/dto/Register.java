package ru.skypro.homework.dto;

import lombok.*;
import ru.skypro.homework.entity.UserEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Register {

    @NotBlank
    @Size(min = 4, max = 32)
    @Email
    private String username;

    @NotBlank
    @Size(min = 8, max = 16)
    private String password;

    @NotBlank
    @Size(min = 2, max = 16)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 16)
    private String lastName;

    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    @NotBlank
    private Role role;

    public UserEntity toUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(this.getUsername());
        userEntity.setPassword(this.getPassword());
        userEntity.setFirstName(this.getFirstName());
        userEntity.setLastName(this.getLastName());
        userEntity.setPhone(this.getPhone());
        userEntity.setRole(this.getRole());
        return userEntity;
    }
}
