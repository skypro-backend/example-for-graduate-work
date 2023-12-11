package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Login
 */

public class LoginDTO {
    @JsonProperty("password")
    private String password;

    @JsonProperty("username")
    private String username;

    public LoginDTO password(String password) {
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

    public LoginDTO username(String username) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LoginDTO loginDTO = (LoginDTO) o;
        return Objects.equals(this.password, loginDTO.password) &&
                Objects.equals(this.username, loginDTO.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, username);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Login {\n");

        sb.append("    password: ").append(toIndentedString(password)).append("\n");
        sb.append("    username: ").append(toIndentedString(username)).append("\n");
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

