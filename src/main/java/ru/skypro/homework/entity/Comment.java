package ru.skypro.homework.entity;

import lombok.*;
import org.apache.catalina.User;

import javax.persistence.*;


@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"pk"})
@RequiredArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @Column(nullable = false)
    private Long createdAt;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "ad_id")
    private Ad ad;
}
