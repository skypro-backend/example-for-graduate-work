package org.openapitools.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * NewPassword
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-29T13:35:35.083731400+03:00[Europe/Moscow]")
public class NewPassword {

  private String currentPassword;

  private String newPassword;

  public NewPassword currentPassword(String currentPassword) {
    this.currentPassword = currentPassword;
    return this;
  }

  /**
   * текущий пароль
   * @return currentPassword
  */
  @Size(min = 8, max = 16) 
  @Schema(name = "currentPassword", description = "текущий пароль", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("currentPassword")
  public String getCurrentPassword() {
    return currentPassword;
  }

  public void setCurrentPassword(String currentPassword) {
    this.currentPassword = currentPassword;
  }

  public NewPassword newPassword(String newPassword) {
    this.newPassword = newPassword;
    return this;
  }

  /**
   * новый пароль
   * @return newPassword
  */
  @Size(min = 8, max = 16) 
  @Schema(name = "newPassword", description = "новый пароль", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("newPassword")
  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewPassword newPassword = (NewPassword) o;
    return Objects.equals(this.currentPassword, newPassword.currentPassword) &&
        Objects.equals(this.newPassword, newPassword.newPassword);
  }

  @Override
  public int hashCode() {
    return Objects.hash(currentPassword, newPassword);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewPassword {\n");
    sb.append("    currentPassword: ").append(toIndentedString(currentPassword)).append("\n");
    sb.append("    newPassword: ").append(toIndentedString(newPassword)).append("\n");
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

