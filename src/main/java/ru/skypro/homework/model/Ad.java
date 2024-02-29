package ru.skypro.homework.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ads")
public class Ad {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ToString.Exclude
    @OneToMany(mappedBy = "ad")
    private List<Comment> comments;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

}
