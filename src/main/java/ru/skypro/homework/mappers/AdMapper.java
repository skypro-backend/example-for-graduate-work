package ru.skypro.homework.mappers;

import org.modelmapper.ModelMapper;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

public class AdMapper {
    private ModelMapper modelMapper;

    public AdMapper(){
        this.modelMapper = new ModelMapper();
    }

    public Ad mapToUser (AdDto adDto){
        return new Ad(adDto);
    }
    public AdDto mapToAdDto(Ad ad) {
        return new AdDto(ad);
    }
}
