package ru.skypro.sitesforresaleofthings.dto;

import lombok.Data;

/**
 * DTO создания или обновления комментария
 */

/**
 * Свойства:
 * 1) text - текст комментария
 */
@Data
public class CreateOrUpdateComment {

    private String text;
}