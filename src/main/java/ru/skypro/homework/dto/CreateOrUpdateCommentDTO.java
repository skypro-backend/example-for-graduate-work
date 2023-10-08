package ru.skypro.homework.dto;

import javax.validation.constraints.Size;

public class CreateOrUpdateCommentDTO {

    @Size(min = 8, max = 64)
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
