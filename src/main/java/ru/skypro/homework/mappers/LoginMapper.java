package ru.skypro.homework.mappers;

import ru.skypro.homework.dto.LoginDto;
import ru.skypro.homework.models.Login;

public class LoginMapper {

    public static Login toLogin(LoginDto loginDto){
        if(loginDto == null){
            throw new NullPointerException("Tried to map null in Login");
        }

        Login login = new Login();

        login.setUsername(loginDto.getUsername());
        login.setPassword(loginDto.getPassword());

        return login;
    }

    public static LoginDto fromLogin(Login login){
        if(login == null){
            throw new NullPointerException("Tried to map null in LoginDto");
        }

        LoginDto loginDto = new LoginDto();

        loginDto.setUsername(login.getUsername());
        loginDto.setPassword(login.getPassword());

        return loginDto;
    }
}
