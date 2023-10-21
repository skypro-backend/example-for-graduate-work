package ru.skypro.homework.projections;

import lombok.Data;

@Data
public class Login {

    private String username; //min 8; max 16
    private String password;//min4;max 32
}
