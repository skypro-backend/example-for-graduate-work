package ru.skypro.homework.dto;

public class Comment {

    private Integer pk;               // id комментария
    private Integer author;           // id автора комментария
    private String authorImage;       // ссылка на аватар автора комментария
    private String authorFirstName;   // имя создателя комментария
    private Long createdAt;           // дата и время создания в мс с 01.01.1970
    private String text;              // текст комментария

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
