package ru.skypro.homework.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
//TODO: cascades, null-notnull fields, types of fields in tables, size
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ad_entity")
public class AdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk")
    private int pk;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;

    @OneToOne
    private ImageEntity imageEntity;

    @Column(name = "price", nullable = false)
    private int price;

    @NotBlank
    @Size(max = 32)
    @Column(name = "title", nullable = false)
    private String title;

    @Size(max = 64)
    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "ad")
    private List<CommentEntity> commentEntities;

}