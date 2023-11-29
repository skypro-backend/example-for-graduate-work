package ru.skypro.homework.projections;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExtendedAd {

    private int pk;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String image;
    private String phone;
    private int price;
    private String title;


//    public ExtendedAd(int pk, String authorFirstName, String authorLastName,
//                      String description, String email, String image, String phone,
//                      int price, String title) {
//        this.pk = pk;
//        this.authorFirstName = authorFirstName;
//        this.authorLastName = authorLastName;
//        this.description = description;
//        this.email = email;
//        this.image = image;
//        this.phone = phone;
//        this.price = price;
//        this.title = title;
//    }
}