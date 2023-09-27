package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity // сущность
@Table (name = "ad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ad {
      private Integer pk; // идентификатор объявления
      private Integer author; // идентификатор автора
      private String title; // заголовок объявления
      private Integer price; // цена объявления
      private String image; //ссылка на картинку объявления
}
