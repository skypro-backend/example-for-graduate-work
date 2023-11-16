package ru.skypro.homework.models;

import lombok.Data;
import ru.skypro.homework.dto.UserDTO;

import javax.persistence.*;
@Entity
@Data
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String filePath;
    private  long fileSize;
    private  String mediaType;
    @Lob
    private  byte[] data;
    @OneToOne
    private Item itemId;
}
