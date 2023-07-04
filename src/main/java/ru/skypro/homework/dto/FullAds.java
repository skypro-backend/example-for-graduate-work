package ru.skypro.homework.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class FullAds {
    private int pk;
    private String authorFirstName;
    private String authorLastName;
    private String description;
//    private String image;
    private String phone;
    private int price;
    private String title;

    public FullAds(int pk, String authorFirstName, String authorLastName, String description, String phone, int price, String title) {
        this.pk = pk;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.description = description;
        this.phone = phone;
        this.price = price;
        this.title = title;
    }

}
