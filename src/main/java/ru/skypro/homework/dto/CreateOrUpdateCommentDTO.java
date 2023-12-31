package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CreateOrUpdateCommentDTO {

    private String text;

    public CreateOrUpdateCommentDTO(String text) {
        this.text = text;
    }
}
