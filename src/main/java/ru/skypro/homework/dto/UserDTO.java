package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import ru.skypro.homework.model.Role;


import javax.validation.constraints.Email;
import java.util.Objects;

/**
 * User
 */

public class UserDTO {
    @JsonProperty("id")
    private Long id;

    @Email
    @JsonProperty("email")
    private String email;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("role")
    private Role role;

    @JsonProperty("image")
    private String image;

    public UserDTO id(Long id) {
        this.id = id;
        return this;
    }

    /**
     * id пользователя
     *
     * @return id
     */
    @ApiModelProperty(value = "id пользователя")


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO email(String email) {
        this.email = email;
        return this;
    }

    /**
     * логин пользователя
     *
     * @return email
     */
    @ApiModelProperty(value = "логин пользователя")


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDTO firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * имя пользователя
     *
     * @return firstName
     */
    @ApiModelProperty(value = "имя пользователя")


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public UserDTO lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * фамилия пользователя
     *
     * @return lastName
     */
    @ApiModelProperty(value = "фамилия пользователя")


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserDTO phone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * телефон пользователя
     *
     * @return phone
     */
    @ApiModelProperty(value = "телефон пользователя")


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserDTO role(Role role) {
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

    public UserDTO image(String image) {
        this.image = image;
        return this;
    }

    /**
     * ссылка на аватар пользователя
     *
     * @return image
     */
    @ApiModelProperty(value = "ссылка на аватар пользователя")


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(this.id, userDTO.id) &&
                Objects.equals(this.email, userDTO.email) &&
                Objects.equals(this.firstName, userDTO.firstName) &&
                Objects.equals(this.lastName, userDTO.lastName) &&
                Objects.equals(this.phone, userDTO.phone) &&
                Objects.equals(this.role, userDTO.role) &&
                Objects.equals(this.image, userDTO.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, firstName, lastName, phone, role, image);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class User {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
        sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
        sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
        sb.append("    role: ").append(toIndentedString(role)).append("\n");
        sb.append("    image: ").append(toIndentedString(image)).append("\n");
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

