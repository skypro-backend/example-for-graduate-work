package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@Entity
@Table(name = "ads")

public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id")
    private Integer pk;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "imageAddress")
    private String imageAddress;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "title")
    private String title;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ad_id")
    private List<Comment> comments;




    public Ad(User user, String description, String imageAddress, Integer price, String title) {
    }

    public Ad(Integer pk, User user, String imageAddress, String description, Integer price, String title, List<Comment> comments) {
        this.pk = pk;
        this.user = user;
        this.imageAddress = imageAddress;
        this.description = description;
        this.price = price;
        this.title = title;
        this.comments = comments;
    }

    public Ad() {

    }
}