package org.openapitools.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * UpdateUser
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-29T13:35:35.083731400+03:00[Europe/Moscow]")
public class UpdateUser {

  private String firstName;

  private String lastName;

  private String phone;

  public UpdateUser firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * имя пользователя
   * @return firstName
  */
  @Size(min = 3, max = 10) 
  @Schema(name = "firstName", description = "имя пользователя", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("firstName")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public UpdateUser lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * фамилия пользователя
   * @return lastName
  */
  @Size(min = 3, max = 10) 
  @Schema(name = "lastName", description = "фамилия пользователя", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lastName")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public UpdateUser phone(String phone) {
    this.phone = phone;
    return this;
  }

  /**
   * телефон пользователя
   * @return phone
  */
  @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}") 
  @Schema(name = "phone", description = "телефон пользователя", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("phone")
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateUser updateUser = (UpdateUser) o;
    return Objects.equals(this.firstName, updateUser.firstName) &&
        Objects.equals(this.lastName, updateUser.lastName) &&
        Objects.equals(this.phone, updateUser.phone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, phone);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateUser {\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
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

