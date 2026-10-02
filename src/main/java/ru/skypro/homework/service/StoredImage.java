package ru.skypro.homework.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StoredImage {

    private byte[] content;
    private String mediaType;
}
