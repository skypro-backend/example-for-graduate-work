package ru.skypro.homework.dto;

/**
 * DTO-класс для представления объявления.
 * Содержит информацию об уникальном идентификаторе, авторе, изображении, цене и названии объявления.
 */
public class Ad {

    private Integer pk;
    private Integer author;
    private String image;
    private Integer price;
    private String title;

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