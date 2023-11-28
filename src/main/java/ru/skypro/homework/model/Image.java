package ru.skypro.homework.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;


import java.util.Objects;

@Entity
@Data
public class Image {

    @Id
    @Column(name = "image_id")
    private String id;

    @Lob
    private byte[] image;


}
