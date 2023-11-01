package ru.skypro.homework.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.entity.Image;
/**
 * The class-wrapper for getting image
 * @author Sulaeva Marina
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {
    private String id;

    private byte [] image;

    /**
     * The method for mapping from entity Image to class-wrapper
     */
    public static ImageDTO fromImage(Image image) {
        return new ImageDTO(image.getId(), image.getImage());
    }
}
