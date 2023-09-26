package ru.skypro.homework.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ad")
@Accessors(chain = true)
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    @Column(nullable = false)
    private String title;

    private String image;

    @Column(nullable = false)
    private Integer price;
    @ManyToOne
    @JoinColumn(name = "author")
    private User user;
}
