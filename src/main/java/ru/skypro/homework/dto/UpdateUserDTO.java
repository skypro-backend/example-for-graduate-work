package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * UpdateUser
 */

public class UpdateUserDTO {
    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("phone")
    private String phone;

    public UpdateUserDTO firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * имя пользователя
     *
     * @return firstName
     */
    @ApiModelProperty(value = "имя пользователя")

    @Size(min = 3, max = 10)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public UpdateUserDTO lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * фамилия пользователя
     *
     * @return lastName
     */
    @ApiModelProperty(value = "фамилия пользователя")

    @Size(min = 3, max = 10)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UpdateUserDTO phone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * телефон пользователя
     *
     * @return phone
     */
    @ApiModelProperty(value = "телефон пользователя")

    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
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
        UpdateUserDTO updateUserDTO = (UpdateUserDTO) o;
        return Objects.equals(this.firstName, updateUserDTO.firstName) &&
                Objects.equals(this.lastName, updateUserDTO.lastName) &&
                Objects.equals(this.phone, updateUserDTO.phone);
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

