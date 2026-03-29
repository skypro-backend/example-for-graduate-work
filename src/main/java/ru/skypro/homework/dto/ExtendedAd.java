package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Расширенная информация об объявлении")
public class ExtendedAd {

    @Schema(description = "id объявления",
            example = "100")
    private int pk;

    @Schema(description = "имя автора объявления",
            example = "John")
    private String authorFirstName;

    @Schema(description = "фамилия автора объявления",
            example = "Doe")
    private String authorLastName;

    @Schema(description = "описание объявления",
            example = "Отличный телефон, в хорошем состоянии")
    private String description;

    @Schema(description = "логин автора объявления",
            example = "john@example.com")
    private String email;

    @Schema(description = "ссылка на картинку объявления",
            example = "/images/ad_1.jpg")
    private String image;

    @Schema(description = "телефон автора объявления",
            example = "+7 (123) 456-78-90")
    private String phone;

    @Schema(description = "цена объявления",
            example = "50000")
    private int price;

    @Schema(description = "заголовок объявления",
            example = "Продам iPhone 13")
    private String title;

    public ExtendedAd() {
    }
    public ExtendedAd(int pk, String authorFirstName, String authorLastName, String description, String email, String image, String phone, int price, String title) {
        this.pk = pk;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.description = description;
        this.email = email;
        this.image = image;
        this.phone = phone;
        this.price = price;
        this.title = title;
    }

    public int getPk() {
        return pk;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public String getImage() {
        return image;
    }

    public String getPhone() {
        return phone;
    }

    public int getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
