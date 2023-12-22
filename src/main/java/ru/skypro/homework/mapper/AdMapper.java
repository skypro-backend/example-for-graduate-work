package ru.skypro.homework.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repositories.AdRepository;
import ru.skypro.homework.repositories.UserRepository;

@RequiredArgsConstructor
@Service
public class AdMapper {

    private final UserRepository repository;

    Ad toEntity(CreateOrUpdateAdDTO adDTO,String userName){
        Ad entity = new Ad();
        entity.setDescription(adDTO.getDescription());
        entity.setPrice(adDTO.getPrice());
        entity.setTitle(adDTO.getTitle());
        return entity;

    }
    AdDTO toEntity(Ad ad){
        AdDTO adDTO = new AdDTO();
        adDTO.setTitle(ad.getTitle());
        adDTO.setPrice(ad.getPrice());
        adDTO.setAuthor(ad.getAuthor().getId());


        return adDTO;

    }
}
