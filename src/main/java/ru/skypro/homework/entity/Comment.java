package ru.skypro.homework.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment")
@Accessors(chain = true)

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;


    @Column(nullable = false)
    private String authorImage;

    @Column(nullable = false, length = 20)
    private String authorFirstName;

    @Column(nullable = false)
    private Integer createdAt;

    @Column(nullable = false)
    private String text;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author")
    private User user;
}
