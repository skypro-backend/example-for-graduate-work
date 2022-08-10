package ru.skypro.homework.dto;

import lombok.Data;
/**
 * Структура данных используемая для создания нового объявления.
 */
@Data
public class CreateAds   {
  private String description;
  private String image;
  private Integer pk;
  private Integer price;
  private String title;
}
