package ru.skypro.homework.dto;

import lombok.Data;

/**
 * Login is class used for user authorization
 * @author AlexBoko
 */
@Data
public class Login {

    private String username;
    private String password;
}
