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

    @Column(nullable = false)
    private Long author;

    @Column(nullable = false)
    private String authorImage;

    @Column(nullable = false)
    private String authorFirstName;

    @Column(nullable = false)
    private Long createdAt;

    @Column(nullable = false)
    private String text;

}
