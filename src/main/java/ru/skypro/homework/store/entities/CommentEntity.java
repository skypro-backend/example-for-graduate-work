package ru.skypro.homework.store.entities;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
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
    Long id;

    @Column(name = "count", nullable = false)
    int count;

    @Column(name = "text", nullable = false)
    String text;

    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "first_name" , referencedColumnName = "first_name")
    UserEntity user;

    @Column(name = "first_name", updatable = false, insertable = false)
    String firstName;
}
