package ru.skypro.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Comment;

import java.util.List;

@Configuration
public class MapperConfig {
    @Bean
    public CommentMapper commentMapper() {
        return new CommentMapper() {
            @Override
            public CommentDto commentToCommentDto(Comment comment) {
                return null;
            }

            @Override
            public Comment commentDtoToComment(CommentDto commentDto) {
                return null;
            }

            @Override
            public Comment updateCommentFromDto(CommentDto commentDto, Comment comment) {
                return null;
            }

            @Override
            public List<CommentDto> commentsToCommentDtos(List<Comment> comments) {
                return null;
            }
        };
    }
}
