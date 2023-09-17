package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateComment {

    @Size(min = 8, max = 64)
    private String text;

}
