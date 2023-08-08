package ru.skypro.flea.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.skypro.flea.dto.CommentDto;
import ru.skypro.flea.dto.CommentsDto;
import ru.skypro.flea.dto.CreateOrUpdateCommentDto;
import ru.skypro.flea.model.Comment;
import ru.skypro.flea.model.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommentMapperTest {

    private static final long defaultTimeStamp = 1000L;

    private static Comment defaultComment;

    private final CommentMapper mapper = Mappers.getMapper(CommentMapper.class);

    @BeforeAll
    public static void fillDefaultComment() {
        User defaultAuthor = new User();
        defaultAuthor.setId(123);
        defaultAuthor.setImage("https://imagehostingservice.org/vh4jbf3.png");
        defaultAuthor.setFirstName("John");

        defaultComment = new Comment();
        defaultComment.setId(456);
        defaultComment.setText("Broken after 10 minutes of use");
        defaultComment.setPublicDate(LocalDateTime.ofInstant(
                Instant.ofEpochMilli(defaultTimeStamp),
                TimeZone.getDefault().toZoneId()));
        defaultComment.setUser(defaultAuthor);
    }

    @Test
    public void commentToCommentDtoMappingTest() {
        CommentDto dto = mapper.toCommentDto(defaultComment);

        assertEntityEqualsToDto(defaultComment, dto);
    }

    @Test
    public void emptyCommentCollectionToCommentDtoListMappingTest() {
        Collection<Comment> emptyEntityList = Collections.emptyList();

        List<CommentDto> dtoList = mapper.toCommentDtoList(emptyEntityList);

        assertTrue(dtoList.isEmpty());
    }

    @Test
    public void commentCollectionToCommentDtoListMappingTest() {
        Collection<Comment> entityList = Collections.singletonList(defaultComment);

        List<CommentDto> dtoList = mapper.toCommentDtoList(entityList);

        assertEquals(dtoList.size(), 1);
        assertEntityEqualsToDto(defaultComment, dtoList.get(0));
    }

    @Test
    public void commentCollectionToCommentsDtoMappingTest() {
        Collection<Comment> entityList = Collections.singletonList(defaultComment);

        CommentsDto dto = mapper.toCommentsDto(entityList);

        assertEquals(dto.getCount(), 1);
        assertEquals(dto.getResults().size(), 1);
        assertEntityEqualsToDto(defaultComment, dto.getResults().get(0));
    }

    @Test
    public void creatingCommentFromCreateOrUpdateCommentDtoTest() {
        CreateOrUpdateCommentDto dto = new CreateOrUpdateCommentDto();
        dto.setText("Comment");

        Comment entity = mapper.createCommentFromDto(dto);

        assertEquals(entity.getText(), dto.getText());
    }

    @Test
    public void updatingCommentFromCreateOrUpdateCommentDtoTest() {
        Comment entity = new Comment();
        entity.setText("Old comment");

        CreateOrUpdateCommentDto dto = new CreateOrUpdateCommentDto();
        dto.setText("New comment");

        mapper.updateCommentFromDto(entity, dto);

        assertEquals(entity.getText(), dto.getText());
    }

    private void assertEntityEqualsToDto(Comment entity, CommentDto dto) {
        assertEquals(dto.getAuthor(), entity.getUser().getId());
        assertEquals(dto.getAuthorImage(), entity.getUser().getImage());
        assertEquals(dto.getAuthorFirstName(), entity.getUser().getFirstName());
        assertEquals(dto.getCreatedAt(), defaultTimeStamp);
        assertEquals(dto.getPk(), entity.getId());
    }

}
