package ru.skypro.homework.model;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CreateOrUpdateAd {
      private String title;  //заголовок объявления
      private String description; // описание объявления
      private Integer price; //цена объявления
}
