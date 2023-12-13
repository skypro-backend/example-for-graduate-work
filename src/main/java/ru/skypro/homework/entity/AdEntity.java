package ru.skypro.homework.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class AdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private Integer price;
    private String title;

    @OneToOne
    @JoinColumn(name = "AD_IMAGE", nullable = true)
    private Image imageAd;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "USER_ID", nullable = true)
    private UserEntity userRelated;

    @OneToMany(mappedBy = "adRelated",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    List<Comment> comments = new ArrayList<>();
}
