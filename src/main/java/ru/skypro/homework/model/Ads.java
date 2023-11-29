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
    private Integer pk;

    private String description;
    private Integer price;
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "user_id")
    private UserInfo author;

    @OneToMany(mappedBy = "ads", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToOne
    @JoinColumn (name = "image_id")
    private Image image;

}
