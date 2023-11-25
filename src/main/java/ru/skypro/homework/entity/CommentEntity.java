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
@Table(name = "comment_entity")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer pk;
    @Column(name = "authorImage")
    private String authorImage;
    @Column(name = "authorFirstName")
    private String authorFirstName;

    @Column(name = "createdAt")
    private String createdAt;
    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "ad_id")
    private AdEntity adEntity;

    @ManyToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;
}
