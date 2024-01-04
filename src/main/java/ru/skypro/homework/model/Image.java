package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String filePath;

    private long fileSize;

    private String mediaType;

    private byte[] data;
}

