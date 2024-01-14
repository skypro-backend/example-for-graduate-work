package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "ad")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false, name = "user_id")
    private UserEntity author;

    @Column(nullable = false)
    @Size(min = 4, max = 32)
    private String tittle;

    @Column(nullable = false)
    @Min(0)
    @Max(1000000)
    private int price;

    @Size(min = 8, max = 64)
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    private ImageEntity image;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ad")
    private List<Comment> comments;

}
