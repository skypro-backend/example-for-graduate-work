package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_id")
    private Integer pk;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    private int price;
    private String title;
    private String description;
    private String image;

    @OneToMany(mappedBy = "ads", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

}
