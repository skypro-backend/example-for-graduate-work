package ru.skypro.homework.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import java.util.Objects;

/**
 * Ads
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-01-16T21:17:34.091476600+03:00[Europe/Moscow]")
@Table(name = "ads")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ads {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    private User author;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @Column(name = "description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;


    public Ads author(Integer author) {
        this.author = author;
        return this;
    }

    /**
     * Get author
     *
     * @return author
     */

    @Schema(name = "author", required = false)
    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public Ads image(String image) {
        this.image = image;
        return this;
    }

    public void addImageItem(String imageItem) {
      this.image = imageItem;
    }

    /**
     * Get image
     *
     * @return image
     */

    @Schema(name = "image", required = false)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Ads pk(Integer pk) {
        this.pk = pk;
        return this;
    }

    /**
     * Get pk
     *
     * @return pk
     */

    @Schema(name = "pk", required = false)
    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public Ads price(Integer price) {
        this.price = price;
        return this;
    }

    /**
     * Get price
     *
     * @return price
     */

    @Schema(name = "price", required = false)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Ads title(String title) {
        this.title = title;
        return this;
    }

    /**
     * Get title
     *
     * @return title
     */

    @Schema(name = "title", required = false)
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
        Ads ads = (Ads) o;
        return Objects.equals(this.author, ads.author) &&
                Objects.equals(this.image, ads.image) &&
                Objects.equals(this.pk, ads.pk) &&
                Objects.equals(this.price, ads.price) &&
                Objects.equals(this.title, ads.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, image, pk, price, title);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Ads {\n");
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

