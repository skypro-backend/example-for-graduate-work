package ru.skypro.homework.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;

    @NotBlank
    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private int price;

    @NotBlank
    @Size(max = 32)
    @Column(nullable = false)
    private String title;

    @Size(max = 64)
    @Column(nullable = true)
    private String description;

}