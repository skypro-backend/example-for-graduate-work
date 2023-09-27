package ru.skypro.homework.validation.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.validation.PriceValidation;

@Service
public class PriceValidationImpl implements PriceValidation {

    @Override
    public Integer checkPrice(Integer price) {
        if (price < 0) {
            return 0;
        }
        return price;
    }
}
