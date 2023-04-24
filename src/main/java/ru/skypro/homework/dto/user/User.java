package ru.skypro.homework.dto.user;

import lombok.Data;
import ru.skypro.homework.model.UserModel;

@Data
public class User {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String image;

    public static User fromModel( UserModel model ) {
        User user = new User();
        user.setId(model.getPk());
        user.setImage("/users/" + model.getPk().toString() + "/avatar");
        user.setEmail(model.getUsername());
        user.setFirstName(model.getFirstName());
        user.setLastName(model.getLastName());
        user.setPhone(model.getPhone());
        return user;
    }

    public UserModel toModel() {
        UserModel model = new UserModel();
        model.setPk(this.getId());
        model.setUsername(this.getEmail());
        model.setFirstName(this.getFirstName());
        model.setLastName(this.getLastName());
        model.setPhone(this.getPhone());
        return model;
    }
}
