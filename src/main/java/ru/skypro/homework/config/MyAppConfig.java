package ru.skypro.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;

@Configuration
public class MyAppConfig {

    @Bean
    public UserMapper userMapper() {
        return new UserMapper() {
            @Override
            public UserDto userToUserDto(User user) {
                return null;
            }

            @Override
            public User userDtoToUser(UserDto userDto) {
                return null;
            }
        };
    }
}
