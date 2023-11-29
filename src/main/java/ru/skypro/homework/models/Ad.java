package ru.skypro.homework.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ad {

private Integer authorId;
private String image;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer pkId;
private Integer price;
private String title;


}
