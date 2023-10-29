package org.openapitools.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Register
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-29T13:35:35.083731400+03:00[Europe/Moscow]")
public class Register {

  private String username;

  private String password;

  private String firstName;

  private String lastName;

  private String phone;

  /**
   * роль пользователя
   */
  public enum RoleEnum {
    USER("USER"),
    
    ADMIN("ADMIN");

    private String value;

    RoleEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static RoleEnum fromValue(String value) {
      for (RoleEnum b : RoleEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private RoleEnum role;

  public Register username(String username) {
    this.username = username;
    return this;
  }

  /**
   * логин
   * @return username
  */
  @Size(min = 4, max = 32) 
  @Schema(name = "username", description = "логин", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("username")
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Register password(String password) {
    this.password = password;
    return this;
  }

  /**
   * пароль
   * @return password
  */
  @Size(min = 8, max = 16) 
  @Schema(name = "password", description = "пароль", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("password")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Register firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * имя пользователя
   * @return firstName
  */
  @Size(min = 2, max = 16) 
  @Schema(name = "firstName", description = "имя пользователя", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("firstName")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Register lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * фамилия пользователя
   * @return lastName
  */
  @Size(min = 2, max = 16) 
  @Schema(name = "lastName", description = "фамилия пользователя", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lastName")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Register phone(String phone) {
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

  public Register role(RoleEnum role) {
    this.role = role;
    return this;
  }

  /**
   * роль пользователя
   * @return role
  */
  
  @Schema(name = "role", description = "роль пользователя", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("role")
  public RoleEnum getRole() {
    return role;
  }

  public void setRole(RoleEnum role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Register register = (Register) o;
    return Objects.equals(this.username, register.username) &&
        Objects.equals(this.password, register.password) &&
        Objects.equals(this.firstName, register.firstName) &&
        Objects.equals(this.lastName, register.lastName) &&
        Objects.equals(this.phone, register.phone) &&
        Objects.equals(this.role, register.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password, firstName, lastName, phone, role);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Register {\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
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

