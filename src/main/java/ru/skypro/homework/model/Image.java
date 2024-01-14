package ru.skypro.homework.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Image {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String filePath;
    private long fileSize;
    private String mediaType;
    private byte[] data;

}
