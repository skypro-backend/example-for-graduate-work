package ru.skypro.homework.dto;

import lombok.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private int author;
    private String authorImage;
    private String authorFirstName;
    private long createdAt;
    private int pk;
    private String text;

    public void setUserEntity(UserEntity userEntity) {
    }

    public void setAdEntity(AdEntity adEntity) {
    }
}
