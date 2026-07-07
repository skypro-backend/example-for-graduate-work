package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comments {

    @Schema(description = "Общее количество комментариев", example = "12")
    private Integer count;

    @Schema(
            description = "Список комментариев к объявлению",
            example = "[" +
                    "{\"pk\": 101, \"text\": \"Отличный товар, спасибо!\", \"author\": 55, \"authorFirstName\": \"Иван\", \"createdAt\": 1715623456789, \"authorImage\": \"https://cdn.example.com/avatar_1.jpg\"}," +
                    "{\"pk\": 102, \"text\": \"Брак, не берите\", \"author\": 56, \"authorFirstName\": \"Мария\", \"createdAt\": 1715624000000, \"authorImage\": \"https://cdn.example.com/avatar_2.jpg\"}" +
                    "]"
    )
    private List<Comment> results;
}
