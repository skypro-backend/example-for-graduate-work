package ru.skypro.homework.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import java.util.List;

/**
 * Сущность объявления, отображающая информацию об объявлении.
 * Включает идентификатор, изображение, название, описание, цену,
 * автора и список комментариев.
 */
@Entity
@Table(name = "advertisements")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "image")
    private String image;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL)
    private List<CommentEntity> comments;
}