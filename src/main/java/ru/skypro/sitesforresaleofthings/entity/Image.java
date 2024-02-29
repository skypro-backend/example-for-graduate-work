package ru.skypro.sitesforresaleofthings.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Создаем сущность "Изображение"
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    private String id;
    private byte[] data;
}