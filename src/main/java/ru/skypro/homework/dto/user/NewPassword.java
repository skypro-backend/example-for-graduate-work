package ru.skypro.homework.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class NewPassword {

     private String currentPassword;

     private String newPassword;
}
