package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.UserModel;

@Data
public class RegisterReq {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;

    public UserModel toModel() {
        UserModel model = new UserModel();
        model.setUsername(this.getUsername());
        model.setPassword(this.getPassword());
        model.setFirstName(this.getFirstName());
        model.setLastName(this.getLastName());
        model.setPhone(this.getPhone());
        return model;
    }
}
