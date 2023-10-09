package ru.skypro.homework.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comments {
    private Integer count;
    private List<CommentView> results;
}
