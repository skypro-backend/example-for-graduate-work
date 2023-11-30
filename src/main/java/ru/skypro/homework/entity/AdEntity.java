package ru.skypro.homework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ads")
public class AdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;
    @NotBlank
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private Integer price;
    @NotBlank
    @Size(max = 32)
    @Column(nullable = false)
    private String title;
    @Size(max = 64)
    @Column(nullable = true)
    private String description;
}
