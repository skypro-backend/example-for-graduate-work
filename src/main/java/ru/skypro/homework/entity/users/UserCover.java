package ru.skypro.homework.entity.users;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
public class UserCover {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String filePath;
    private String fileSize;
    private String mediaType;
    @Lob
    private byte[] preview;
    @OneToOne
    @JoinColumn(name = "user_id")
   // @JoinColumn(name = "image")
    private User user;

    public UserCover() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCover userCover = (UserCover) o;
        return Objects.equals(id, userCover.id) && Objects.equals(filePath, userCover.filePath) && Objects.equals(fileSize, userCover.fileSize) && Objects.equals(mediaType, userCover.mediaType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filePath, fileSize, mediaType);
    }
}
