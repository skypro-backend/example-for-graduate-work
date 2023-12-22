package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

/**
 * DTO for list of advertisement
 */

@Data
public class AdsDto {
    private Integer count;

    private List<AdDto> results;
}
