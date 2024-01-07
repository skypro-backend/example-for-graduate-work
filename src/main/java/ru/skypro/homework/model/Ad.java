package ru.skypro.homework.model;

import lombok.Data;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.UserDto;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "ad")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer price;

    private String title;

    private String description;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @OneToMany(mappedBy = "ad")
    private List<Comment> comments;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;
}

