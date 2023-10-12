package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class CommentDTO {
    private Integer pk;
    private Instant createdAt;
    private String text;
    private UserDTO userDTO;
    private AdDTO adDTO;

}