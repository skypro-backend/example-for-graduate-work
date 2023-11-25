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
    private long price;
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "user_id")
    private UserInfo author;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ads")
    private List<Comment> comments;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "image_id")
    private Image imageModel;

}
