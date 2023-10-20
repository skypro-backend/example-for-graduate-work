package ru.skypro.homework.dto.ads;

//import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateAd {

    //    @Size(min = 4, max = 32)
    private String title;

    //    @Size(max = 10000000)
    private int price;

    //    @Size(min = 8, max = 64)
    private String description;

}