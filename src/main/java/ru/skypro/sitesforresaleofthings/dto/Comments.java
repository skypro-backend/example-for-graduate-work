package ru.skypro.sitesforresaleofthings.dto;

import lombok.Data;

import java.util.List;

/**
 * DTO комментариев
 */

/**
 * Свойства:
 * 1) count - общее количество комментариев,
 * 2) results - список комментариев
 */
@Data
public class Comments {

    private Integer count;
    private List<Comment> results;
}