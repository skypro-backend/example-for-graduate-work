package ru.skypro.homework.store.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "ad")
public class AdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int author;

    @Column(name = "first_name", nullable = false)
    String authorFirstName;

    @Column(name = "last_name", nullable = false)
    String authorLastName;

    @Column(name = "pk", nullable = false)
    int pk;

    @Column(name = "price", nullable = false)
    int price;

    @Column(name = "title", nullable = false)
    int title;

    @Column(name = "image", nullable = false)
    String image;

    @Column(name = "description", nullable = false)
    String description;

    @Column(name = "email", nullable = false)
    String email;

    @Column(name = "phone", nullable = false)
    String phone;


}
