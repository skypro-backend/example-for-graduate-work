package ru.skypro.homework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ad")
public class AdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //dto - pkAdId

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;//dto - authorId

    @NotBlank
    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private Integer price;

    @NotBlank
    @Size(min = 4, max = 32)
    @Column(nullable = false)
    private String title;

    @Size(min = 8, max = 64)
    @Column
    private String description;

    @OneToMany(mappedBy = "ad")
    private List<CommentEntity> comments;
}
