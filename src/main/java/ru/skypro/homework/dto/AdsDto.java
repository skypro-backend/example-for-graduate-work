package ru.skypro.homework.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// объявление для публикации
@XmlRootElement(name = "Ads")
public class AdsDto {
    // id объявления
    private int author;
    // иллюстрация объявления
    private String image;
    //
    private int pk;
    // цена
    private int price;
    // описание объявления
    private String title;
    @XmlElement(name = "author")
    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }
    @XmlElement(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    @XmlElement(name = "pk")
    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }
    @XmlElement(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    @XmlElement(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
