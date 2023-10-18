package ru.skypro.homework.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.entity.Users;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUser {
    private String firstName;
    private String lastName;
    private String phone;

    public static UpdateUser toUpdateUser(Users users) {
        UpdateUser updateUser = new UpdateUser();
        updateUser.setPhone(users.getPhone());
        updateUser.setFirstName(users.getFirstName());
        updateUser.setLastName(users.getLastName());
        return updateUser;
    }
}