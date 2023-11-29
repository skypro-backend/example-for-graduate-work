package ru.skypro.homework.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "images")
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filePath;
    private long fileSize;
    private String mediaType;
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] data;
/*    @OneToOne
    @JoinColumn(name = "ad_id")
    private Ad ad;*/
}
