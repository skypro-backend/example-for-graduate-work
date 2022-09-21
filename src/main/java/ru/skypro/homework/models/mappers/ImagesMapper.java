package ru.skypro.homework.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.models.entity.Images;

import java.io.IOException;


@Mapper(componentModel = "spring")
public interface ImagesMapper {

    @Mapping(target = "filePath", expression = "java(file.getResource().getFilename())")
    @Mapping(target = "fileSize", expression = "java((int) (file.getSize()))")
    @Mapping(target = "mediaType", expression = "java(file.getContentType())")
    @Mapping(target = "pk", ignore = true)
    @Mapping(target = "data", expression = "java(file.getBytes())")
    Images fromFile (MultipartFile file) throws IOException;

}
