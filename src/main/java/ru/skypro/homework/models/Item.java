package ru.skypro.homework.models;

import lombok.Data;
import ru.skypro.homework.dto.UserDTO;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    @OneToOne
    private Photo photo;
    @OneToOne
    private UserDTO userId;
}
