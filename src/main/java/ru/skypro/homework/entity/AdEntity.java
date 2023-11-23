package ru.skypro.homework.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
public class AdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "author")
    @NotNull
    private UserEntity author;

    @NotBlank
    private String image;

    @NotNull
    private int price;

    @NotBlank
    @Size(max = 255)
    private String title;

}