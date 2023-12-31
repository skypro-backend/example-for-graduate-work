package ru.skypro.homework.model.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;
import ru.skypro.homework.dto.AdsDto;

/**
 * <h2>AdsFound</h2>results of finding advertisements by some criteria, for instance by user id
 *
 * <h3>Fields</h3>
 * <p>
 * result: {@link AdsDto} contains list of DTO of Ad entities
 * <br>
 * httpStatus: {@link HttpStatus} depending on search results
 */
@Data
public class AdsFound {
    AdsDto result;
    HttpStatus httpStatus;
}
