package org.openapitools.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * CreateOrUpdateAd
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-29T13:35:35.083731400+03:00[Europe/Moscow]")
public class CreateOrUpdateAd {

  private String title;

  private Integer price;

  private String description;

  public CreateOrUpdateAd title(String title) {
    this.title = title;
    return this;
  }

  /**
   * заголовок объявления
   * @return title
  */
  @Size(min = 4, max = 32) 
  @Schema(name = "title", description = "заголовок объявления", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public CreateOrUpdateAd price(Integer price) {
    this.price = price;
    return this;
  }

  /**
   * цена объявления
   * minimum: 0
   * maximum: 10000000
   * @return price
  */
  @Min(0) @Max(10000000) 
  @Schema(name = "price", description = "цена объявления", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("price")
  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public CreateOrUpdateAd description(String description) {
    this.description = description;
    return this;
  }

  /**
   * описание объявления
   * @return description
  */
  @Size(min = 8, max = 64) 
  @Schema(name = "description", description = "описание объявления", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
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
    CreateOrUpdateAd createOrUpdateAd = (CreateOrUpdateAd) o;
    return Objects.equals(this.title, createOrUpdateAd.title) &&
        Objects.equals(this.price, createOrUpdateAd.price) &&
        Objects.equals(this.description, createOrUpdateAd.description);
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

