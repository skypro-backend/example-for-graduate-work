package ru.skypro.homework.dto;

public class Ad {
    private Integer author;
    private String image;
    private Integer pk;
    private Integer price;
    private String title;
    public int getAuthorId() {
        return author;
    }

    public void setAuthorId(int authorId) {
        this.author = authorId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
