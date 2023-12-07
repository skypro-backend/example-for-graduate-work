package ru.skypro.homework.entity;
import lombok.*;
import javax.persistence.*;
import java.util.Collection;

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
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Integer price;
    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;
    @OneToOne
    @JoinColumn(name = "image_entity_path")
    private ImageEntity imageEntity;
    @OneToMany(mappedBy = "adId")
    private Collection<CommentEntity> commentEntities;

}
