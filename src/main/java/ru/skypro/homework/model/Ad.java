package ru.skypro.homework.model;

import org.hibernate.annotations.Comment;

import java.util.Collection;

public class Ad {

    private Integer id;
    private Integer price;
    private String title;
    private String description;

    private String imagePath;
    private String userAuthor;
    private Collection<Comment> comments;



}
