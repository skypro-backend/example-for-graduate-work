package org.openapitools.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import java.util.Objects;

/**
 * Ad
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-29T13:35:35.083731400+03:00[Europe/Moscow]")
public class Ad {

  private Integer author;

  private String image;

  private Integer pk;

  private Integer price;

  private String title;

  public Ad author(Integer author) {
    this.author = author;
    return this;
  }

  /**
   * id автора объявления
   * @return author
  */
  
  @Schema(name = "author", description = "id автора объявления", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("author")
  public Integer getAuthor() {
    return author;
  }

  public void setAuthor(Integer author) {
    this.author = author;
  }

  public Ad image(String image) {
    this.image = image;
    return this;
  }

  /**
   * ссылка на картинку объявления
   * @return image
  */
  
  @Schema(name = "image", description = "ссылка на картинку объявления", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("image")
  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Ad pk(Integer pk) {
    this.pk = pk;
    return this;
  }

  /**
   * id объявления
   * @return pk
  */
  
  @Schema(name = "pk", description = "id объявления", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pk")
  public Integer getPk() {
    return pk;
  }

  public void setPk(Integer pk) {
    this.pk = pk;
  }

  public Ad price(Integer price) {
    this.price = price;
    return this;
  }

  /**
   * цена объявления
   * @return price
  */
  
  @Schema(name = "price", description = "цена объявления", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("price")
  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public Ad title(String title) {
    this.title = title;
    return this;
  }

  /**
   * заголовок объявления
   * @return title
  */
  
  @Schema(name = "title", description = "заголовок объявления", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("title")
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
    Ad ad = (Ad) o;
    return Objects.equals(this.author, ad.author) &&
        Objects.equals(this.image, ad.image) &&
        Objects.equals(this.pk, ad.pk) &&
        Objects.equals(this.price, ad.price) &&
        Objects.equals(this.title, ad.title);
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

