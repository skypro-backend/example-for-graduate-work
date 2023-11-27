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

    @Column(name = "author_first_name")
    private String authorFirstName;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "ad_id")
    private AdEntity adId;

    @OneToOne
    @JoinColumn(name = "image_entity_path")
    private ImageEntity authorImage;

    @ManyToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;
}
