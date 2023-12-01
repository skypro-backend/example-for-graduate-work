package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * NewPassword
 */

public class NewPasswordDTO {
    @JsonProperty("currentPassword")
    private String currentPassword;

    @JsonProperty("newPassword")
    private String newPassword;


    public NewPasswordDTO(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }


    /**
     * текущий пароль
     *
     * @return currentPassword
     */
    @ApiModelProperty(value = "текущий пароль")

    @Size(min = 8, max = 16)
    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public NewPasswordDTO newPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }

    /**
     * новый пароль
     *
     * @return newPassword
     */
    @ApiModelProperty(value = "новый пароль")

    @Size(min = 8, max = 16)
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
        NewPasswordDTO newPasswordDTO = (NewPasswordDTO) o;
        return Objects.equals(this.currentPassword, newPasswordDTO.currentPassword) &&
                Objects.equals(this.newPassword, newPasswordDTO.newPassword);
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

