package ru.skypro.homework.dto.ads;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Users;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateAd {

    @Size(min = 4, max = 32)
    private String title;

    @Size(max = 10000000)
    private int price;

    @Size(min = 8, max = 64)
    private String description;

    public Ad toAd(Users user){
        Ad ad = new Ad();
        ad.setUser(user);
        ad.setTitle(this.getTitle());
        ad.setPrice(this.getPrice());
        return ad;
    }

}