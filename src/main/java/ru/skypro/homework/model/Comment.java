package ru.skypro.homework.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "ad_id")
    private Ad ad;

    @Column(nullable = false)
    private Long createdAt;

    @Column(nullable = false)
    private String text;

}
