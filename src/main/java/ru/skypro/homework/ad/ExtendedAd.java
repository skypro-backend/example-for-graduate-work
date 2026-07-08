package ru.skypro.homework.ad;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Подробная информация об объявлении")
public class ExtendedAd {

    @Schema(description = "Код объявления", example = "102356")
    private Integer pk;

    @Schema(description = "Заголовок объявления", example = "Велосипед")
    private String title;

    @Schema(description = "Описание", example = "прекрасный товар, практически новый")
    private String description;

    @Schema(description = "Цена товара", example = "100")
    private Integer price;

    @Schema(description = "Фото товара", example = "-картинка-")
    private String image;

    @Schema(description = "Имя автора", example = "Иван")
    private String authorFirstName;

    @Schema(description = "Фамилия автора", example = "Иванов")
    private String authorLastName;

    @Schema(description = "Логин пользователя", example = "user@example.com")
    private String email;

    @Schema(description = "Телефон пользователя", example = "+79000000000")
    private String phone;

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
