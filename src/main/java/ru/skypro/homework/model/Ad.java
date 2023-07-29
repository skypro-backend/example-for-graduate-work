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
@AllArgsConstructor
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

    public Ad(User user, String description, String imageAddress, Integer price, String title) {
    }



    public Ad() {

    }
}