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

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ImageEntity getImageEntity() {
        return imageEntity;
    }

    public void setImageEntity(ImageEntity imageEntity) {
        this.imageEntity = imageEntity;
    }

    public Collection<CommentEntity> getCommentEntities() {
        return commentEntities;
    }

    public void setCommentEntities(Collection<CommentEntity> commentEntities) {
        this.commentEntities = commentEntities;
    }
}
