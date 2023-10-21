package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ads")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_id",nullable = false)
    private Integer pk; //id объявления

    @Column(name = "title", nullable = false)
    private String title; //заголовок объявления

    @Column(name="description")
    private String description; // описание объявления

    @Column(name = "price", nullable = false)
    private int price; //цена объявления

    @Column(name = "image")
    private String image; //  путь к картинке объявления

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Users user; //id автора объявления

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Comment> comments; //комментарии к объявлению






}
