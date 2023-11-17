package ru.skypro.homework.models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    @OneToOne
    private PhotoEntity photo;
    @OneToOne
    private UserEntity userId;
}
