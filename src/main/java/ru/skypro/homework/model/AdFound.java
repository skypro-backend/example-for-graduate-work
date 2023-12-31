package ru.skypro.homework.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * <h2>AdFound</h2>
 * Representing results of finding Ad entity in repository
 */
@Data
public class AdFound {

    private Ad ad;
    HttpStatus httpStatus;

}
