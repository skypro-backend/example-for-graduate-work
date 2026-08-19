package ru.skypro.homework.model;

import lombok.Data;
import ru.skypro.homework.dto.Comment;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ads")
@Data
public class AdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long price;
    private String title;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private UserEntity author; // Ссылка на Entity

    private String image;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> comments;
}