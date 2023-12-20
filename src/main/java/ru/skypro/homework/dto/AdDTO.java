package ru.skypro.homework.dto;

import java.util.Objects;


public class AdDTO {

    private Integer author;    // id автора объявления
    private String image;  // ссылка на картинку объявления
    private Integer pk;        // id объявления
    private String price;  // цена объявления
    private String title;  // заголовок объявления



    public AdDTO(Integer author, String image, Integer pk, String price, String title) {
        this.author = author;
        this.image = image;
        this.pk = pk;
        this.price = price;
        this.title = title;
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

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdDTO adDTO = (AdDTO) o;
        return Objects.equals(author, adDTO.author) && Objects.equals(image, adDTO.image) && Objects.equals(pk, adDTO.pk) && Objects.equals(price, adDTO.price) && Objects.equals(title, adDTO.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, image, pk, price, title);
    }

    @Override
    public String toString() {
        return "AdDTO{" +
                "author=" + author +
                ", image='" + image + '\'' +
                ", pk=" + pk +
                ", price='" + price + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
