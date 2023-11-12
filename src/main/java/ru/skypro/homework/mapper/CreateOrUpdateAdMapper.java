package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreateOrUpdateAdMapper {
    @Mapping(target = "title", source = "createOrUpdateAdDto.title")
    @Mapping(target = "price", source = "createOrUpdateAdDto.price")
    @Mapping(target = "description", source = "createOrUpdateAdDto.description")
    Ad toModel(CreateOrUpdateAdDto createOrUpdateAdDto);
}
