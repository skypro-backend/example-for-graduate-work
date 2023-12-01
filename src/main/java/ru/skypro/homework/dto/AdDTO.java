package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

public class AdDTO {
    @JsonProperty("author")
    private Long author;

    @JsonProperty("image")
    private String image;

    @JsonProperty("pk")
    private Integer pk;

    @JsonProperty("price")
    private Integer price;

    @JsonProperty("title")
    private String title;


    public AdDTO author(Long author) {
        this.author = author;
        return this;
    }

    /**
     * id автора объявления
     *
     * @return author
     */
    @ApiModelProperty(value = "id автора объявления")


    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public AdDTO image(String image) {
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

    public AdDTO pk(Integer pk) {
        this.pk = pk;
        return this;
    }

    /**
     * id объявления
     *
     * @return pk
     */
    @ApiModelProperty(value = "id объявления")


    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public AdDTO price(Integer price) {
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

    public AdDTO title(String title) {
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
        AdDTO adDTO = (AdDTO) o;
        return Objects.equals(this.author, adDTO.author) &&
                Objects.equals(this.image, adDTO.image) &&
                Objects.equals(this.pk, adDTO.pk) &&
                Objects.equals(this.price, adDTO.price) &&
                Objects.equals(this.title, adDTO.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, image, pk, price, title);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Ad {\n");

        sb.append("    author: ").append(toIndentedString(author)).append("\n");
        sb.append("    image: ").append(toIndentedString(image)).append("\n");
        sb.append("    pk: ").append(toIndentedString(pk)).append("\n");
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

