package ru.skypro.homework.projections;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.DiscriminatorColumn;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateOrUpdateComment {
    //    Необходимо ограничить min 8 and max 64

    private String text;
}
