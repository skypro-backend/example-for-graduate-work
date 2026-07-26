package ru.skypro.homework.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.skypro.homework.constants.ApiConstants;

@Data
@Entity
@Table(name = "ads")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false, length = ApiConstants.AD_TITLE_LENGTH)
    private String title;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "description", nullable = false, length = ApiConstants.AD_DESCRIPTION_LENGTH)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(name = "image", length = ApiConstants.IMAGE_PATH_LENGTH)
    private String image;
}
