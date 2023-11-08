package ru.skypro.homework;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class MapperUtil {
    @Bean
    public ModelMapper getMapper() {
        return new ModelMapper();
    }
}
