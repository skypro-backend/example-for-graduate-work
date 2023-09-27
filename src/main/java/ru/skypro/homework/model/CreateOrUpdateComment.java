package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity // сущность
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateComment {
      private String text; // текст комментария
}
