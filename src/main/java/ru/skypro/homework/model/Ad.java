package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "ads")
@Data
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;
    @ManyToOne
    private User author;
    @OneToOne
    private Image image;
    private int price;
    private String title;
    private String description;


    @JsonIgnore
    @OneToMany(mappedBy = "ad")
    private Collection<Comment> comments;
}
