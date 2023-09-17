package ru.skypro.homework.dto;

<<<<<<< HEAD
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
=======
import lombok.Data;

@Data
>>>>>>> 1a3f150 (CommentsController)
public class Ad {

    private int author;
    private String image;
    private int pk;
    private int price;
    private String title;

}
