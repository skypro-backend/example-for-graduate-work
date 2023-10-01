package ru.skypro.homework.dto.comment;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateComment {

    private String text;
}
