package ru.skypro.homework.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

/**
 * Ads
 */

@Entity
@Table(name = "ads")
@Data
@NoArgsConstructor
public class Ads {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NonNull
    private ProfileUser author;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "ads", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Image> images;

}