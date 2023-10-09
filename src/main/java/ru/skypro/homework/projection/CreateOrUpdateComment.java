package ru.skypro.homework.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateOrUpdateComment {
    @Size(min = 8, max = 64)
    private String text;
}
