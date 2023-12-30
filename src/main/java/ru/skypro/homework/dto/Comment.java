package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private Instant createdAt;
    private Integer pk;
    private String text;
    private Integer adId;

}
