package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer pk;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;
    private int price;
    private String image;
    private String title;
    private String description;
}
