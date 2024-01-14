package ru.skypro.homework.target;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.model.Avatar;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-01-13T16:42:46+0300",
        comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment commentDtoToComment(CommentDto dto) {
        if ( dto == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setId( dto.getPk() );
        comment.setText( dto.getText() );

        return comment;
    }

    @Override
    public CommentDto commentToCommentDto(Comment entity) {
        if ( entity == null ) {
            return null;
        }

        CommentDto commentDto = new CommentDto();

        Long id = entityAuthorId( entity );
        if ( id != null ) {
            commentDto.setAuthor( id );
        }
        commentDto.setAuthorFirstName( entityAuthorFirstName( entity ) );
        if ( entity.getId() != null ) {
            commentDto.setPk( entity.getId() );
        }
        commentDto.setCreatedAt( entity.getCreatedAt() );
        commentDto.setAuthorImage( avatarToString( entityAuthorAvatar( entity ) ) );
        commentDto.setText( entity.getText() );

        return commentDto;
    }

    private Long entityAuthorId(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        User author = comment.getAuthor();
        if ( author == null ) {
            return null;
        }
        Long id = author.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityAuthorFirstName(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        User author = comment.getAuthor();
        if ( author == null ) {
            return null;
        }
        String firstName = author.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private Avatar entityAuthorAvatar(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        User author = comment.getAuthor();
        if ( author == null ) {
            return null;
        }
        Avatar avatar = author.getAvatar();
        if ( avatar == null ) {
            return null;
        }
        return avatar;
    }
}
