package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "User information")
public class User {

    @Schema(description = "User ID")
    private Integer id;

    @Schema(description = "User email")
    private String email;

    @Schema(description = "First name")
    private String firstName;

    @Schema(description = "Last name")
    private String lastName;

    @Schema(description = "Phone number")
    private String phone;

    @Schema(description = "User role")
    private Role role;

    @Schema(description = "Avatar URL")
    private String image;
}