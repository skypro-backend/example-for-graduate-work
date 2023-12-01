package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * ExtendedAd
 */

public class ExtendedAdDTO {
    @JsonProperty("pk")
    private Long pk;

    @JsonProperty("authorFirstName")
    private String authorFirstName;

    @JsonProperty("authorLastName")
    private String authorLastName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("email")
    private String email;

    @JsonProperty("image")
    private String image;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("price")
    private Integer price;

    @JsonProperty("title")
    private String title;

    public ExtendedAdDTO pk(Long pk) {
        this.pk = pk;
        return this;
    }

    /**
     * id объявления
     *
     * @return pk
     */
    @ApiModelProperty(value = "id объявления")


    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public ExtendedAdDTO authorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
        return this;
    }

    /**
     * имя автора объявления
     *
     * @return authorFirstName
     */
    @ApiModelProperty(value = "имя автора объявления")


    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public ExtendedAdDTO authorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
        return this;
    }

    /**
     * фамилия автора объявления
     *
     * @return authorLastName
     */
    @ApiModelProperty(value = "фамилия автора объявления")


    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public ExtendedAdDTO description(String description) {
        this.description = description;
        return this;
    }

    /**
     * описание объявления
     *
     * @return description
     */
    @ApiModelProperty(value = "описание объявления")


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExtendedAdDTO email(String email) {
        this.email = email;
        return this;
    }

    /**
     * логин автора объявления
     *
     * @return email
     */
    @ApiModelProperty(value = "логин автора объявления")


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ExtendedAdDTO image(String image) {
        this.image = image;
        return this;
    }

    /**
     * ссылка на картинку объявления
     *
     * @return image
     */
    @ApiModelProperty(value = "ссылка на картинку объявления")


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ExtendedAdDTO phone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * телефон автора объявления
     *
     * @return phone
     */
    @ApiModelProperty(value = "телефон автора объявления")


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ExtendedAdDTO price(Integer price) {
        this.price = price;
        return this;
    }

    /**
     * цена объявления
     *
     * @return price
     */
    @ApiModelProperty(value = "цена объявления")


    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public ExtendedAdDTO title(String title) {
        this.title = title;
        return this;
    }

    /**
     * заголовок объявления
     *
     * @return title
     */
    @ApiModelProperty(value = "заголовок объявления")


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExtendedAdDTO extendedAdDTO = (ExtendedAdDTO) o;
        return Objects.equals(this.pk, extendedAdDTO.pk) &&
                Objects.equals(this.authorFirstName, extendedAdDTO.authorFirstName) &&
                Objects.equals(this.authorLastName, extendedAdDTO.authorLastName) &&
                Objects.equals(this.description, extendedAdDTO.description) &&
                Objects.equals(this.email, extendedAdDTO.email) &&
                Objects.equals(this.image, extendedAdDTO.image) &&
                Objects.equals(this.phone, extendedAdDTO.phone) &&
                Objects.equals(this.price, extendedAdDTO.price) &&
                Objects.equals(this.title, extendedAdDTO.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, authorFirstName, authorLastName, description, email, image, phone, price, title);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExtendedAd {\n");

        sb.append("    pk: ").append(toIndentedString(pk)).append("\n");
        sb.append("    authorFirstName: ").append(toIndentedString(authorFirstName)).append("\n");
        sb.append("    authorLastName: ").append(toIndentedString(authorLastName)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    image: ").append(toIndentedString(image)).append("\n");
        sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
        sb.append("    price: ").append(toIndentedString(price)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

