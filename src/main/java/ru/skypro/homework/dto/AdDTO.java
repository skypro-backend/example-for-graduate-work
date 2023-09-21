package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AdDTO {
    private Integer author;
    private String image;
    private Integer pk;
    private String price;
    private String title;
}
