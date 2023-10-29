package ru.skypro.homework.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import ru.skypro.homework.utils.MyMapper;

public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public MyMapper myMapper(ModelMapper modelMapper) {
        return new MyMapper(modelMapper);
    }
}
