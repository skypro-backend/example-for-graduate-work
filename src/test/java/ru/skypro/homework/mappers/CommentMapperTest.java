package ru.skypro.homework.mappers;


import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.comments.out.CommentDto;
import ru.skypro.homework.dto.comments.out.CommentsDto;
import ru.skypro.homework.entity.ads.Ad;
import ru.skypro.homework.entity.comments.Comment;
import ru.skypro.homework.entity.users.User;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CommentMapperTest {

    private final CommentMapper commentMapper = Mappers.getMapper(CommentMapper.class);

    @Test
    void shouldToCommentDto() {
        //given
        Comment comment = new Comment();
        comment.setPk(125);
        comment.setText("text of comment");
        comment.setCreatedAt(LocalDateTime.now());
        User testAuthor = new User();
        testAuthor.setId(15);
        testAuthor.setImage("author image");
        testAuthor.setFirstName("Vasiliy");
        comment.setAuthor(testAuthor);
        Ad ad = new Ad();
        ad.setAuthor(testAuthor);

        //when
        CommentDto commentDto = commentMapper.toCommentDto(comment);
        System.out.println(commentDto);

        //then
        assertThat(commentDto).isNotNull();
        assertThat(commentDto.getPk().intValue()).isEqualTo(comment.getPk());
        assertThat(commentDto.getAuthor().intValue()).isNotEqualTo(comment.getAuthor());
        assertThat(commentDto.getAuthorFirstName()).isEqualTo(comment.getAuthor().getFirstName());
        assertThat(commentDto.getAuthorImage()).isEqualTo(comment.getAuthor().getImage());
        assertThat(commentDto.getText()).isEqualTo(comment.getText());
        assertThat(commentDto.getCreatedAt().intValue()).isNotEqualTo(comment.getCreatedAt());
    }


    @Test
    void shouldToCommentDtoList() {

        //given
        User author1 = new User();
        author1.setId(200);
        author1.setImage("avatar of author1");
        author1.setFirstName("Nikolay");

        Comment comment1 = new Comment();
        comment1.setAuthor(author1);
        comment1.setPk(100);
        comment1.setText("text of comment1");

        comment1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1, 1));

        Ad ad1 = new Ad();
        ad1.setAuthor(author1);

        User author2 = new User();
        author2.setId(400);
        author1.setImage("avatar of author2");
        author1.setFirstName("Alexey");

        Comment comment2 = new Comment();
        comment2.setAuthor(author2);
        comment2.setPk(10050);
        comment2.setText("text of comment2");
        comment2.setCreatedAt(LocalDateTime.of(2, 2, 2, 2, 2, 2));

        Ad ad2 = new Ad();
        ad2.setAuthor(author2);

        List<Comment> commentList = List.of(comment1, comment2);

        //when
        CommentsDto commentsDto = commentMapper.toCommentsDto(commentList);
        System.out.println(commentsDto);

        //then
        assertThat(commentsDto).isNotNull();
        assertThat(commentsDto.getCount()).isEqualTo(commentList.size());
        assertThat(commentsDto.getResults()).isNotNull();
    }
}