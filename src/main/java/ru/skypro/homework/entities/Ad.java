package ru.skypro.homework.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table(name = "Ads")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long pk;

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    @OneToMany
    @Column(name = "comments")
    private List<Comment> comments;

    @Column(name = "title")
    private String title;


    @Column(name = "price")
    private int price;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;
}
