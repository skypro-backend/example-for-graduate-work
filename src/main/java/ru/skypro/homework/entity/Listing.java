package ru.skypro.homework.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public abstract class Listing implements List<Listing> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer price;
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;

    @OneToOne
    private Image image;
}
