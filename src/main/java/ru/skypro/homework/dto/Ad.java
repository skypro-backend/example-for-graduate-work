package ru.skypro.homework.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ad {

    private Integer author;
    private String image;
    private Integer pk;
    private Integer price;
    private String title;
}
