package ru.skypro.homework.entity;
import lombok.*;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad_entity")
public class AdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id; //
    private String description;
    private Integer price;
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_entity_path")
    private ImageEntity imageEntity;
    @OneToMany(mappedBy = "adEntity",cascade = CascadeType.ALL)
    private List<CommentEntity> commentEntities;


}
