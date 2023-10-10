package ru.skypro.homework.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateComment {

    @NotBlank(message = "Это поле не может быть пустым")
    @Size(min = 8, max = 64, message = "Текст объявления должен содержать не менее 8 и не более 64 символов")
    private String text;

}
