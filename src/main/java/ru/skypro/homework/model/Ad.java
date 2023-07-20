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
    private Long pk;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String imageAddress;

    private String description;

    private Integer price;

    private String title;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ad_id")
    private List<Comment> comments;
}