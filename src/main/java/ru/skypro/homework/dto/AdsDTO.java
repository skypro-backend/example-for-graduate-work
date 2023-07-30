package ru.skypro.homework.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.skypro.homework.model.Ad;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class AdsDTO {
    private int authorId;
    private String image;
    private int pk;
    private int price;
    private String title;

    public AdsDTO(int authorId, String image, int pk, int price, String title) {
        this.authorId = authorId;
        this.image=image;
        this.pk = pk;
        this.price = price;
        this.title = title;
    }

}
