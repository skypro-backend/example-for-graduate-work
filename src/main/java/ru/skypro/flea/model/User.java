package ru.skypro.flea.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.skypro.flea.model.enums.Role;

@Data
public class User {

    @Schema(description = "User's id")
    private Integer id;

    @Schema(description = "User's login (e-mail)")
    private String email;

    @Schema(description = "User's first name")
    private String firstName;

    @Schema(description = "User's last name")
    private String lastName;

    @Schema(description = "User's phone")
    private String phone;

    @Schema(description = "User's role")
    private Role role;

    @Schema(description = "User's avatar link")
    private String image;

}
