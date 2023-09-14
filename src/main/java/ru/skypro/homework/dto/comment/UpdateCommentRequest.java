package ru.skypro.homework.dto.comment;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Objects;


public class UpdateCommentRequest {
    private String text;

    public UpdateCommentRequest(String text) {
        this.text = text;
    }

    public UpdateCommentRequest() {
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
        UpdateCommentRequest that = (UpdateCommentRequest) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "UpdateCommentRequest{" +
                "text='" + text + '\'' +
                '}';
    }
}