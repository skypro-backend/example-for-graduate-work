package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.LoginDto;
import ru.skypro.homework.model.Login;

@Component
public class LoginMapper {

        public LoginDto mapToDTO(Login login) {
            return new LoginDto(
                    login.getId(),
                    login.getUsername(),
                    login.getPassword()
            );
        }

        public Login mapToEntity(LoginDto loginDto) {
            return new Login(
                    loginDto.getId(),
                    loginDto.getUsername(),
                    loginDto.getPassword()
            );
        }
}
