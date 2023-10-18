package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ads")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_id",nullable = false)
    private int pk; //id объявления

    @Max(32)
    @Min(4)
    @Column(name = "title", nullable = false)
    private String title; //заголовок объявления

    @Max(64)
    @Min(8)
    @Column(name="description")
    private String description; // описание объявления

    @Max(10000000)
    @Column(name = "price", nullable = false)
    private int price; //цена объявления

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "image_id")
    private Image image; //картинка объявления

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user; //id автора объявления

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Comment> comments; //комментарии к объявлению






}
