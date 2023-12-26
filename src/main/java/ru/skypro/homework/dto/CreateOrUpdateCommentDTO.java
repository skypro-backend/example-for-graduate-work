package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateCommentDTO {

    @NotBlank
    @Size(min = 8)
    private String text;
}
