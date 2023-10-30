package ru.skypro.homework.entity;


import lombok.*;
import org.apache.catalina.User;

import javax.persistence.*;
import java.awt.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"pk"})
@RequiredArgsConstructor
@Entity
public class Ad {

    @Id
    @Column(name = "ad_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @OneToOne
    @ToString.Exclude
    @JoinColumn(name = "image_id")
    private Image image;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String title;
}
