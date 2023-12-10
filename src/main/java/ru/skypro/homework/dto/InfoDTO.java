package ru.skypro.homework.dto;


import lombok.Data;

@Data
public class InfoDTO {

    private Long infoId;
    private Long authorId;
    private String userName;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String image;
    private String phone;
    private Double price;
    private String title;

}
