package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false, length = 64)
    private String description;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "image_id")
    private Image image;

}
