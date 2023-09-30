package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class ExtendedAd {

    int adId;
    String authorFirstName;
    String authorLastName;
    String descriptionAd;
    String authorLogin;
    String linkToImage;
    String authorPhone;
    int adPrice;
    String adTitle;

}
