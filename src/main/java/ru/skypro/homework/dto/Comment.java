package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Информация о комментарии")
public class Comment {

    @Schema(description = "id автора комментария")
    private int author;

    @Schema(description = "ссылка на аватар автора комментария")
    private String authorImage;

    @Schema(description = "имя создателя комментария")
    private String authorFirstName;

    @Schema(description = "дата и время создания комментария в миллисекундах")
    private long createdAt;

    @Schema(description = "id комментария")
    private int pk;

    @Schema(description = "текст комментария")
    private String text;

    public Comment(int author, String authorImage, String authorFirstName, long createdAt, int pk, String text) {
        this.author = author;
        this.authorImage = authorImage;
        this.authorFirstName = authorFirstName;
        this.createdAt = createdAt;
        this.pk = pk;
        this.text = text;
    }

    public int getAuthor() {
        return author;
    }

    public String getAuthorImage() {
        return authorImage;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public int getPk() {
        return pk;
    }

    public String getText() {
        return text;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public void setText(String text) {
        this.text = text;
    }
}
