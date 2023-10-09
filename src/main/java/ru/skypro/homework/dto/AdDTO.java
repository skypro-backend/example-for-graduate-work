package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class AdDTO {
    private UserDTO author;
    private String image;
    private Integer pk;
    private Integer price;
    private String title;
    private String description;
}
