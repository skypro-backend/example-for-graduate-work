package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * CreateOrUpdateAd
 */

public class CreateOrUpdateAdDTO {
    @JsonProperty("title")
    private String title;

    @JsonProperty("price")
    private Integer price;

    @JsonProperty("description")
    private String description;

    public CreateOrUpdateAdDTO title(String title) {
        this.title = title;
        return this;
    }

    /**
     * заголовок объявления
     *
     * @return title
     */
    @ApiModelProperty(value = "заголовок объявления")

    @Size(min = 4, max = 32)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CreateOrUpdateAdDTO price(Integer price) {
        this.price = price;
        return this;
    }

    /**
     * цена объявления
     * minimum: 0
     * maximum: 10000000
     *
     * @return price
     */
    @ApiModelProperty(value = "цена объявления")

    @Min(0)
    @Max(10000000)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public CreateOrUpdateAdDTO description(String description) {
        this.description = description;
        return this;
    }

    /**
     * описание объявления
     *
     * @return description
     */
    @ApiModelProperty(value = "описание объявления")

    @Size(min = 8, max = 64)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateOrUpdateAdDTO createOrUpdateAdDTO = (CreateOrUpdateAdDTO) o;
        return Objects.equals(this.title, createOrUpdateAdDTO.title) &&
                Objects.equals(this.price, createOrUpdateAdDTO.price) &&
                Objects.equals(this.description, createOrUpdateAdDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, description);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateOrUpdateAd {\n");

        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    price: ").append(toIndentedString(price)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

