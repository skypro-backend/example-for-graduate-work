package org.openapitools.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import java.util.Objects;

/**
 * ExtendedAd
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-29T13:35:35.083731400+03:00[Europe/Moscow]")
public class ExtendedAd {

  private Integer pk;

  private String authorFirstName;

  private String authorLastName;

  private String description;

  private String email;

  private String image;

  private String phone;

  private Integer price;

  private String title;

  public ExtendedAd pk(Integer pk) {
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

  public ExtendedAd authorFirstName(String authorFirstName) {
    this.authorFirstName = authorFirstName;
    return this;
  }

  /**
   * имя автора объявления
   * @return authorFirstName
  */
  
  @Schema(name = "authorFirstName", description = "имя автора объявления", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("authorFirstName")
  public String getAuthorFirstName() {
    return authorFirstName;
  }

  public void setAuthorFirstName(String authorFirstName) {
    this.authorFirstName = authorFirstName;
  }

  public ExtendedAd authorLastName(String authorLastName) {
    this.authorLastName = authorLastName;
    return this;
  }

  /**
   * фамилия автора объявления
   * @return authorLastName
  */
  
  @Schema(name = "authorLastName", description = "фамилия автора объявления", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("authorLastName")
  public String getAuthorLastName() {
    return authorLastName;
  }

  public void setAuthorLastName(String authorLastName) {
    this.authorLastName = authorLastName;
  }

  public ExtendedAd description(String description) {
    this.description = description;
    return this;
  }

  /**
   * описание объявления
   * @return description
  */
  
  @Schema(name = "description", description = "описание объявления", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ExtendedAd email(String email) {
    this.email = email;
    return this;
  }

  /**
   * логин автора объявления
   * @return email
  */
  
  @Schema(name = "email", description = "логин автора объявления", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public ExtendedAd image(String image) {
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

  public ExtendedAd phone(String phone) {
    this.phone = phone;
    return this;
  }

  /**
   * телефон автора объявления
   * @return phone
  */
  
  @Schema(name = "phone", description = "телефон автора объявления", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("phone")
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public ExtendedAd price(Integer price) {
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

  public ExtendedAd title(String title) {
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
    ExtendedAd extendedAd = (ExtendedAd) o;
    return Objects.equals(this.pk, extendedAd.pk) &&
        Objects.equals(this.authorFirstName, extendedAd.authorFirstName) &&
        Objects.equals(this.authorLastName, extendedAd.authorLastName) &&
        Objects.equals(this.description, extendedAd.description) &&
        Objects.equals(this.email, extendedAd.email) &&
        Objects.equals(this.image, extendedAd.image) &&
        Objects.equals(this.phone, extendedAd.phone) &&
        Objects.equals(this.price, extendedAd.price) &&
        Objects.equals(this.title, extendedAd.title);
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

