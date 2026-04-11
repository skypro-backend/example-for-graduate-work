package ru.skypro.homework.dto;

/**
 * DTO-класс, представляющий комментарий к объявлению.
 * Содержит информацию об уникальном идентификаторе, авторе, аватаре, имени автора, времени создания и тексте комментария.
 */
public class Comment {

    private Integer pk;
    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private Long createdAt;
    private String text;

    public Comment() {
    }


    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
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

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
