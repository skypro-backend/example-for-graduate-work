package ru.skypro.homework.mapper;

import lombok.extern.slf4j.Slf4j;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import ru.skypro.homework.dto.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.Image;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CommentMapper {

    @Mapping(target = "author", source = "user.id")
    @Mapping(target = "authorImage", source = "user.image",
            qualifiedByName = "imageMapping")
    @Mapping(target = "authorFirstName", source = "user.firstName")
    @Mapping(target = "pk", source = "id")
    public abstract Comment commentEntityToComment(CommentEntity commentEntity);

    @Mapping(target = "createdAt", expression = "java(getCurrentTime())")
    public abstract CommentEntity createOrUpdateCommentToCommentEntity(CreateOrUpdateComment createOrUpdateComment);


    protected String getImageUrl(Image image) {
        return "/images/" + image.getId();
    }

    @Named("imageMapping")
    public String imageMapping(Image image) {
        if (image == null) {
            return null;
        }
        return getImageUrl(image);
    }

    public static long getCurrentTime() {
        long timeDifference = 0;
        SimpleDateFormat obj = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        obj.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            Date initial = obj.parse("01-01-1970 00:00:00");
            LocalDateTime time = LocalDateTime.now();
            Date now = Timestamp.valueOf(time);
            timeDifference = now.getTime() - initial.getTime();
        } catch (ParseException e) {
            log.error("Failed to parse the date", e);
        }
        return timeDifference;
    }
}