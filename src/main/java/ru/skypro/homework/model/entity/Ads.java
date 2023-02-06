package ru.skypro.homework.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Ads
 */

@Entity
@Table(name = "ads")
@Data
public class Ads {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private ProfileUser author;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "ads", cascade = CascadeType.ALL)
    private List<Image> images;
}