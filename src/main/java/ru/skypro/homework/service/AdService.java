package ru.skypro.homework.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.model.Ad;

@Service
public class AdService {

    private final ModelMapper mapper;

    public AdService(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public AdDTO toDTO(Ad ad) {
        return mapper.map(ad, AdDTO.class);
    }

    public Ad toEntity(AdDTO dto) {
        return mapper.map(dto, Ad.class);
    }
}
