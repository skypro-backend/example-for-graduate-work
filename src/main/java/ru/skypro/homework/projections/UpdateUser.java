package ru.skypro.homework.projections;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UpdateUser {
    private String firstName; // min3, max 10
    private String lastName;//min3; max 10
    private String phone; //	pattern: \+7\s?\(?\d{3}\)?\s?\d{3}-?\d{2}-?\d{2}
}