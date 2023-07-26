package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

    public class UserDetailsDto {

        private final String username;
        private final String password;

        private final Integer userId;
        private final RoleDto roleDto;

        public UserDetailsDto(String username, String password, Integer userId, RoleDto roleDto) {
            this.username = username;
            this.password = password;
            this.userId = userId;
            this.roleDto = roleDto;
        }
    }
