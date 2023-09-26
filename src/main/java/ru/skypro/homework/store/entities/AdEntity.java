package ru.skypro.homework.store.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.skypro.homework.dto.UserDTO;

import javax.persistence.*;

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
    UserEntity author; //user

    @Column(name = "price", nullable = false)
    int price;

    @Column(name = "title", nullable = false)
    int title;

    @Column(name = "image", nullable = false)
    String image;

    @Column(name = "description", nullable = false)
    String description;
    @Column(name = "phone", nullable = false)
    String phone;

}
