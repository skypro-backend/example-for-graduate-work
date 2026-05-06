package ru.skypro.homework.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CommentRequestDto {
    @NotBlank
    @Size(min = 8, max = 64)
    private String text;
}
