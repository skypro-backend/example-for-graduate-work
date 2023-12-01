package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import ru.skypro.homework.model.Role;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Register
 */

public class RegisterDTO {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("phone")
    private String phone;


    @JsonProperty("role")
    private Role role;

    public RegisterDTO username(String username) {
        this.username = username;
        return this;
    }

    /**
     * логин
     *
     * @return username
     */
    @ApiModelProperty(value = "логин")

    @Size(min = 4, max = 32)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RegisterDTO password(String password) {
        this.password = password;
        return this;
    }

    /**
     * пароль
     *
     * @return password
     */
    @ApiModelProperty(value = "пароль")

    @Size(min = 8, max = 16)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RegisterDTO firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * имя пользователя
     *
     * @return firstName
     */
    @ApiModelProperty(value = "имя пользователя")

    @Size(min = 2, max = 16)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public RegisterDTO lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * фамилия пользователя
     *
     * @return lastName
     */
    @ApiModelProperty(value = "фамилия пользователя")

    @Size(min = 2, max = 16)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public RegisterDTO phone(String phone) {
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

    public RegisterDTO role(Role role) {
        this.role = role;
        return this;
    }

    /**
     * роль пользователя
     *
     * @return role
     */
    @ApiModelProperty(value = "роль пользователя")


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
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
        RegisterDTO registerDTO = (RegisterDTO) o;
        return Objects.equals(this.username, registerDTO.username) &&
                Objects.equals(this.password, registerDTO.password) &&
                Objects.equals(this.firstName, registerDTO.firstName) &&
                Objects.equals(this.lastName, registerDTO.lastName) &&
                Objects.equals(this.phone, registerDTO.phone) &&
                Objects.equals(this.role, registerDTO.role);
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

