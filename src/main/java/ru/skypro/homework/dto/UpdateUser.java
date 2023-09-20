package ru.skypro.homework.dto;

import lombok.*;
import ru.skypro.homework.entity.UserEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUser {

    @NotBlank
    @Size(min = 3, max = 10)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 10)
    private String lastName;

    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    public static UpdateUser fromUserEntity(UserEntity userEntity) {
        UpdateUser updateUser = new UpdateUser();
        updateUser.setFirstName(userEntity.getFirstName());
        updateUser.setLastName(userEntity.getLastName());
        updateUser.setPhone(userEntity.getPhone());
        return updateUser;
    }

    public UserEntity toUserEntity () {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(this.getFirstName());
        userEntity.setLastName(this.getLastName());
        userEntity.setPassword(this.getPhone());
        return userEntity;
    }

}
