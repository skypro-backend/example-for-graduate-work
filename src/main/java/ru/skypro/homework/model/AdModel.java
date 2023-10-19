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

    @Column(name = "user_id")
    @JoinColumn(name = "author_id")
    private int author;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel userModel;

    @OneToMany(mappedBy="ad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommentModel> commentModels;

//    @Column(name = "comments")
//    @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY)
//    private Collection<CommentModel> commentsList;


}
