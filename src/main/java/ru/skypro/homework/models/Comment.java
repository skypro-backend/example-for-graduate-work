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
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "text")
    private String text;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne(optional = false)
    @JoinColumn(name = "ad_id",referencedColumnName = "id")
    private Ad ad;

    @PrePersist
    private void init() {
        createdAt = LocalDateTime.now();
    }
}
