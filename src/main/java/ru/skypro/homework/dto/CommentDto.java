package ru.skypro.homework.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

public record CommentDto(

            //id автора комментария
            Integer author,
            //ссылка на аватар автора комментария
            String authorImage,
            @NotBlank(message = "Имя автора не может быть пустым")
            @Size(min = 2, max = 50, message = "Имя автора комментария должно быть от 2 до 20 символов")
            String authorFirstName,
            @PastOrPresent(message = "Поле createdAt должно содержать прошедшую или текущую дату и время создания " +
                    "комментария в миллисекундах с 00:00:00 01.01.1970")
            Long createdAt,
             //id комментария
            Integer pk,
            @NotBlank(message = "Текст комментария не может быть пустым")
            @Size(min = 2, max = 500, message = "Текст комментария должно быть от 8 до 64 символов")
            String text
    ) {
    }
