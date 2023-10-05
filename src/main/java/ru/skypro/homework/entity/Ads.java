package ru.skypro.homework.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int pk;


    String authorFirstName;
    String authorLastName;

    String email;

    String phone;
    String image;
    String title;
    Integer price;
    String description;
}

