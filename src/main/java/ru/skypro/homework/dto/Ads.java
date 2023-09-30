package ru.skypro.homework.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Data
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Ads<T> {

    private int count;
    private Collection<T> results;

    public static <T> Ads<Comments> of(Collection<T> results) {
        Ads<T> Ads = new Ads<>();
        if (results.isEmpty()) {
            Ads.results = new ArrayList<>();
            return (ru.skypro.homework.dto.Ads<Comments>) Ads;
        }
        Ads.results = results;
        Ads.count = results.size();
        return (ru.skypro.homework.dto.Ads<Comments>) Ads;
    }

}
