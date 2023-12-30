package ru.skypro.homework.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment_entity")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_at")
    private Instant createdAt;

    private String text;

    @ManyToOne
    @JoinColumn(name = "ad_id")
    private AdEntity adEntity;

    @ManyToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;

}
