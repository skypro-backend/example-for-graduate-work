package ru.skypro.homework.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ads")
@Data
public class AdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer price;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = true)
    private String imageUrl;
}