package ru.skypro.homework.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ad_entity")
public class AdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false, length = 32)
    private String title;

    @Column(nullable = false, length = 64)
    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "image_entity_id", nullable = false)
    private ImageEntity imageEntity;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.REMOVE)
    private List<CommentEntity> commentEntities;

}