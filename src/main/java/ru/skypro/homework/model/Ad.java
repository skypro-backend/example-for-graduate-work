package ru.skypro.homework.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "ads")
@Data
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    private String image;
    private Integer price;
    private String title;
    private String description;

    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.BinaryType")
    private byte[] imageData;
}
