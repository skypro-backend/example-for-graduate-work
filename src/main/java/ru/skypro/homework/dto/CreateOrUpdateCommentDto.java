package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateCommentDto {
    @Size(min=8,max=64)
    private String text;
}
