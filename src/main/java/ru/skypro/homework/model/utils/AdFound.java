package ru.skypro.homework.model.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;
import ru.skypro.homework.model.Ad;

/**
 * <h2>AdFound</h2>
 * Representing results of finding Ad entity in repository:<br> Ad entity <br> Httpstatus depending on search results
 */
@Data
public class AdFound {

    private Ad ad;
    HttpStatus httpStatus;

    /**
     * <h2>adNotFound()</h2>
     *
     * @return {@link AdFound} with null Ad entity and NOT_FOUND HttpsStatus
     */
    public static AdFound adNotFound() {
        AdFound a = new AdFound();
        a.setAd(null);
        a.setHttpStatus(HttpStatus.NOT_FOUND);
        return a;
    }

}
