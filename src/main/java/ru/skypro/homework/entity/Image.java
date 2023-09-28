package ru.skypro.homework.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Image {
    @Id
    private String id;

    private byte[] image;
}
