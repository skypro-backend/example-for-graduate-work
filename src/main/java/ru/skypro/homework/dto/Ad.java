package ru.skypro.homework.dto;

public class Ad {
    private Integer pk;          // id объявления
    private Integer author;      // id автора объявления
    private String image;        // ссылка на картинку объявления
    private Integer price;       // цена объявления
    private String title;        // заголовок объявления

    public Ad() {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}