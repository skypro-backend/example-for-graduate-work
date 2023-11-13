package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ads")
@Data
public class Ad {

    @Column(name = "id_author")
    private int author;
    @Column(name = "author_Path_Image")
    private String image;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;
    @Column(name = "price")
    private int price;
    @Column(name = "ad_title")
    private String title;

}
