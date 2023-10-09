package ru.skypro.homework.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

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

    private String description;

    private String image;

    @Column(nullable = false)
    private Integer price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author")
    private User user;
    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;
}
