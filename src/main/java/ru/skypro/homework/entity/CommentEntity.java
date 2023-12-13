package ru.skypro.homework.entity;

import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "comment_entity")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk")
    private int pk;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "ad_id", nullable = false)
    private AdEntity ad;

    @OneToOne
    private ImageEntity authorImage;

    @NotBlank
    @Column(name = "author_first_name")
    private String authorFirstName;

    @NotNull
    @Column(name = "created_at")
    private Long createdAt;


    @NotBlank
    @Size(max = 255)
    @Column(name = "text")
    private String text;

}