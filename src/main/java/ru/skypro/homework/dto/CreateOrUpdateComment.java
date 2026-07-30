package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Данные создания или изменения комментария.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateComment {

    @NotBlank
    @Size(min = 8, max = 64)
    private String text;
}
