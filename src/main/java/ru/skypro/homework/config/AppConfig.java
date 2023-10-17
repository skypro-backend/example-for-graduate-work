package ru.skypro.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.mapper.AdvertMapper;
import ru.skypro.homework.model.Advert;

import java.util.List;

@Configuration
public class AppConfig {
    @Bean
    public AdvertMapper advertMapper() {
        return new AdvertMapper() {
            @Override
            public AdDto advertToAdvertDto(Advert advert) {
                return null;
            }

            @Override
            public Advert advertDtoToAdvert(AdDto advertDto) {
                return null;
            }

            @Override
            public Advert updateAdFromDto(AdDto advertDto, Advert advert) {
                return null;
            }

            @Override
            public List<AdDto> advertsToAdvertDtos(List<Advert> adverts) {
                return null;
            }
        };
    }
}
