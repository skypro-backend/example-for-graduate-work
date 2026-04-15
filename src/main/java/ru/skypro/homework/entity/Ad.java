package ru.skypro.homework.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ads")
@Data
@NoArgsConstructor
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer price;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();
}
