package ru.skypro.homework.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(exclude = "pk")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    private Integer price;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;
}
