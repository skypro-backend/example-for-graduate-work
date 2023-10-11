package ru.skypro.homework.service.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "ad")
public class AdEntity {

    @Column(name = "pk", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int pk;

    @ManyToOne
    @JoinColumn(name = "id")
    UserEntity user; //user

    @Column(name = "price", nullable = false)
    int price;

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "image", nullable = false)
    String image;

    @Column(name = "description", nullable = false)
    String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "adEntity")
    List<CommentEntity> commentEntityList = new ArrayList<>();
}
