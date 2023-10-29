package ru.skypro.homework.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"user", "ad"})
@ToString(exclude = {"user", "ad"})
@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer pk;
    @Column(name = "text")
    private String text;
    @Column(name = "creat_at")
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "ad_id",referencedColumnName = "ad_id")
    private Ad ad;

    @PrePersist
    private void init() {
        createdAt = LocalDateTime.now();
    }
}
