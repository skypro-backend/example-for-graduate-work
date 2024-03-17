package ru.skypro.homework.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Модель Объявления
 * связанные таблицы UserDto (много AdsDto к одному UserDto)
 * и лист с комментариями (у одного AdsDto много комментов)
 */
@Entity
@Getter
@Setter
@NoArgsConstructor(force = true)
@Table(name = "ADS", indexes = {
        @Index(name = "IDX_ADS_USER", columnList = "USER_ID")
})
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;


    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;


    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "IMAGE_ID")
    private String image;

    @OneToMany(mappedBy = "ads", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> adsCommentList;
}
