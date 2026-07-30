package ru.skypro.homework.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Объявление пользователя.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ad")
public class AdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "title",
            nullable = false,
            length = 32
    )
    private String title;

    @Column(
            name = "price",
            nullable = false
    )
    private Integer price;

    @Column(
            name = "description",
            nullable = false,
            length = 64
    )
    private String description;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image", columnDefinition = "bytea")
    private byte[] image;

    @Column(
            name = "image_media_type",
            length = 100
    )
    private String imageMediaType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "author_id",
            nullable = false
    )
    private UserEntity author;

    @OneToMany(
            mappedBy = "ad",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<CommentEntity> comments = new ArrayList<>();
}
