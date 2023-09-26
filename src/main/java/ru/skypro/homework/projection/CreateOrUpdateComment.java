package ru.skypro.homework.projection;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Size;

@RequiredArgsConstructor
@Data
public class CreateOrUpdateComment {
    @Size(min = 8, max = 64)
    private final String text;
}
