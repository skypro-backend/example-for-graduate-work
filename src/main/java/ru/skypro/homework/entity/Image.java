package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] image;

    @OneToOne(mappedBy = "imageAvatar")
    private UserEntity user;

    @OneToOne(mappedBy = "imageAd")
    private AdEntity ad;

}
