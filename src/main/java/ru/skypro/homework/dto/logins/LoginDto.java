package ru.skypro.homework.dto.logins;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.Objects;


public class LoginDto {
    @Size(min = 4, max = 32)
    String username;
    @Size(min = 8, max = 16)
    String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginDto)) return false;
        LoginDto loginDto = (LoginDto) o;
        return Objects.equals(getUsername(), loginDto.getUsername()) && Objects.equals(getPassword(), loginDto.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
