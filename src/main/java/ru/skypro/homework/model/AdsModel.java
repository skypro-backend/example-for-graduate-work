package ru.skypro.homework.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ads")
@Getter
@Setter
public class AdsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private UserModel user;
    @OneToMany(mappedBy = "ads", cascade = CascadeType.ALL)
    private List<CommentModel> comments;
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] image;
    private Integer price;
    private String title;
    private String description;


}
