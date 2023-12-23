package ru.skypro.homework.dto;


import java.util.Objects;

public class CreateOrUpdateCommentDTO {

    private String text; //текст комментария

    public CreateOrUpdateCommentDTO() {
    }

    public CreateOrUpdateCommentDTO(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateOrUpdateCommentDTO that = (CreateOrUpdateCommentDTO) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "CreateOrUpdateCommentDTO{" +
                "text='" + text + '\'' +
                '}';
    }
}
