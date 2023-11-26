package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Image {

    private long id;
    private String filePath;
    private long fileSize;
    private String mediaType;
    private byte[] data;
    private String link;
}
