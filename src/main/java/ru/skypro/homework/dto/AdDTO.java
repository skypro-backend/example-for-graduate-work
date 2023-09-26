package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.OneToMany;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class AdDTO {
    private Integer author;
    private String image;
    private Integer pk;
    private Integer price;
    private String title;
}
