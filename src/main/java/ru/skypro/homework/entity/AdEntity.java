package ru.skypro.homework.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ad_entity")
public class AdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk")
    private Integer pk; // id объявления

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;

    @NotBlank
    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "price", nullable = false)
    private int price;

    @NotBlank
    @Size(max = 32)
    @Column(name = "title", nullable = false)
    private String title;

    @Size(max = 64)
    @Column(name = "description", nullable = true)
    private String description;

    @OneToMany(mappedBy = "ad")
    private List<CommentEntity> commentEntities;

}