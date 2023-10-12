package ru.skypro.homework.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(exclude = "pk")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    private String text;
    private Long createdAt;
    @ManyToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;
}
