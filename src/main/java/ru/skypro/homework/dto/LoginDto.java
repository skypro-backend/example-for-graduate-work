package ru.skypro.homework.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor(force = true)
public record LoginDto(
        /**
         minLength: 4, maxLength: 16
         логин
         */
        String username,
        /**
         minLength: 4, maxLength: 16
         логин
         */
        String password
) {

}
