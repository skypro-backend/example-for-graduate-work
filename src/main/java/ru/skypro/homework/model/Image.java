package ru.skypro.homework.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class Image {

    @Id
    @Column(name = "image_id")
    private String id;

    @Lob
    private byte[] image;

    //private String filePath;
    //private long fileSize;
    //private String mediaType;
}
