package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.skypro.homework.entity.Ad;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class FullAdDto {

    @JsonProperty("pk")
    private Integer pk;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String image;
    private String phone;
    private Integer price;
    private String title;

    public static FullAdDto fromAd(Ad ad) {
        FullAdDto dto = new FullAdDto();
        dto.setPk(ad.getPk());
        dto.setAuthorFirstName(ad.getUser().getFirstName());
        dto.setAuthorLastName(ad.getUser().getLastName());
        dto.setDescription(ad.getDescription());
        dto.setEmail(ad.getUser().getUsername());
        if (ad.getImage() != null) {
            dto.setImage(String.format("/ads/image/%s", ad.getImage()));
        } else {
            dto.setImage(null);
        }
        dto.setPhone(ad.getUser().getPhone());
        dto.setPrice(ad.getPrice());
        dto.setTitle(ad.getTitle());

        return dto;
    }

}
