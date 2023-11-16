package ru.skypro.homework.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ru.skypro.homework.dto.UserDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private LocalDateTime date;
    @OneToOne
    private UserDTO userId;
}
