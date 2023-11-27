package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Image {
    private Integer id;
    private String filePath;
    private Integer fileSize;
    private String mediaType;
    private byte[] data;
}