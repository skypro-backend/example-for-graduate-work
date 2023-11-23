package ru.skypro.homework.entity;

import lombok.*;
import ru.skypro.homework.dto.Comment;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude = "pk")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad")
public class AdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer pk;
    private Integer author; // айДи автора обьявления
    private Integer price;
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "adEntity")
    private List<CommentEntity> commentEntityList;


}
