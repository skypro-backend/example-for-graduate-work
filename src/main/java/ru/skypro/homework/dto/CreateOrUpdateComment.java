package ru.skypro.homework.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateComment {

    @NotBlank
    @Size(min = 8, max = 64)
    private String text;

}
