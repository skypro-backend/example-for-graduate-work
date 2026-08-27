package ru.skypro.homework.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Table;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
@NoArgsConstructor
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    @Column(nullable = false)
    private byte[] data;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private long fileSize;

    public ImageEntity(byte[] data, String contentType, long fileSize) {
        this.data = data;
        this.contentType = contentType;
        this.fileSize = fileSize;
    }
}
