package ru.skypro.homework.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

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

/*
        Тоже нужно по наверное
    @Column(name = "comments")
    @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY)
    private Collection<CommentModel> commentsList;
*/

}
