package ru.skypro.homework.model;

import jakarta.persistence.*;
import lombok.Data;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.AdsInfoDTO;

import java.util.List;


@Entity
@Data
public class Ads {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ads_id")
    private long id;

    private String description;
    private String image;
    private long price;
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    UserInfo author;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "ads")
    List<Comment> comments;

    public Ads(String description, long price, String title) {
        this.description = description;
        this.price = price;
        this.title = title;
    }


    public static AdsDTO mapToAdsDto(Ads ads) {
        return new AdsDTO(ads.getAuthor().getId(),
                ads.getAdsImage(ads),
                ads.getId(),
                ads.getPrice(),
                ads.getTitle());
    }

    public static AdsInfoDTO mapToAdsInfoDto(Ads ads) {
        return new AdsInfoDTO(ads.getId(),
                ads.getAuthor().getFirstName(),
                ads.getAuthor().getLastName(),
                ads.getDescription(),
                ads.getAuthor().getEmail(),
                ads.getAdsImage(ads),
                ads.getAuthor().getPhone(),
                ads.getPrice(),
                ads.getTitle());
    }

    public String getAdsImage(Ads ads) {
        return null;
    }
}
