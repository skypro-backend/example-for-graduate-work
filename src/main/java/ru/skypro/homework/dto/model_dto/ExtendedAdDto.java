package ru.skypro.homework.dto.model_dto;

import lombok.Data;

@Data
public class ExtendedAdDto {
      private Integer pk; // уникальный идентификатор объявления
      private String authorFirstName; // имя автора объявления
      private String authorLastName; // фамилия автора объявления.
      private String description; // описание объявления
      private String email; // электронная почта автора объявления
      private String image; // ссылка на изображение объявления
      private String phone; // номер телефона автора объявления
      private Integer price; // цена объявления
      private String title; // заголовок объявления

}
