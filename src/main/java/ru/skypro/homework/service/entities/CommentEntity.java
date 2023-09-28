package ru.skypro.homework.service.entities;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "count", nullable = false)
    int count;

    @Column(name = "text", nullable = false)
    String text;

    @Column(name = "created_at", nullable = false)
    Long createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    UserEntity user;

    @ManyToOne
    @JoinColumn(name = "ad_id", referencedColumnName = "pk")
    AdEntity adEntity;

}
