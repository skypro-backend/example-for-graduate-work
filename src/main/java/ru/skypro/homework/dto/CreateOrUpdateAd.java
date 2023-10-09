package ru.skypro.homework.dto;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateAd {
    @Size(min = 4, max = 32)
    private String title;
    @Min(0)
    @Max(1000000)
    private int price;
    @Size(min = 8, max = 64)
    private String description;
}
