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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "user_id")
    UserInfo author;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ads")
    List<Comment> comments;

    @OneToOne (cascade = CascadeType.ALL)
    private Image imageModel;

    public String getAdsImage(Ads ads) {
        return null;
    }
}
