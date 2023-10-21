package ru.skypro.homework.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "ad")

public class AdModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_id")
    private int pk;

    @Column(name = "ad_image")
    private String image;

    @Column(name = "price")
    private int price;

    @Column(name = "title")
    private String title;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel userModel;

    @OneToMany(mappedBy="adModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommentModel> commentModels;

}
