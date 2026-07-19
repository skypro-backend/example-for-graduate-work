package ru.skypro.homework.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.model.Ad;

@Service
public class AdMapper {

    private final ModelMapper mapper;

    public AdMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public AdDTO toDTO(Ad ad) {
        return mapper.map(ad, AdDTO.class);
    }

    public Ad toEntity(AdDTO dto) {
        return mapper.map(dto, Ad.class);
    }

    public void updateEntityFromDTO(CreateOrUpdateAdDTO dto, Ad existingAd) {
        mapper.map(dto, existingAd);
    }
}
