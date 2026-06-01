package ru.skypro.homework.comment;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Комментарий")
public class Comment {

    @Schema(description = "Номер комментария", example = "1256")
    private Integer pk;

    @Schema(description = "Текст комментария", example = "привет. хочу посмотреть")
    private String text;

    @Schema(description = "Дата создания комментария", example = "01.01.2000")
    private Long createdAt;

    @Schema(description = "Логин автора", example = "user@example.com")
    private Integer author;

    @Schema(description = "Фото автора", example = "-картинка-")
    private String authorImage;

    @Schema(description = "Имя автора", example = "Иван")
    private String authorFirstName;

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public String getAuthorImage() {
        return authorImage;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }
}
