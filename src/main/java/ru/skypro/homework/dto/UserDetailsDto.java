package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

    public class UserDetailsDto {

        private final String username;
        private final String password;

        private final Integer userId;
        private final Role role;

        public UserDetailsDto(String username, String password, Integer userId, Role role) {
            this.username = username;
            this.password = password;
            this.userId = userId;
            this.role = role;
        }
    }
