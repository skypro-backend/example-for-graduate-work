package ru.skypro.homework.dto.listing;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListingDTO extends ListingsDTO {
    private Long author;
    private String image;
    private Long pk;
    private Integer price;
    private String title;

    public void setAuthor(Long author) {
        this.author = author;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
