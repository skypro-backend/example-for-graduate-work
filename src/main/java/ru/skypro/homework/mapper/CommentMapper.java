package ru.skypro.homework.mapper;


import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.model.Comment;

@Service
public class CommentMapper {

    //из model в dto
    public CommentDTO mapToCommentDto(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setAuthor(comment.getAuthor());
        commentDTO.setAuthorFirstName(comment.getAuthorFirstName());
        commentDTO.setAuthorImage(comment.getAuthorImage());
        commentDTO.setCreatedAt(comment.getCreatedAt());
        commentDTO.setPk(comment.getPk());
        commentDTO.setText(comment.getText());
        return commentDTO;
    }

    //из dto в model
    public Comment mapToComment(CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setAuthor(commentDTO.getAuthor());
        comment.setAuthorFirstName(commentDTO.getAuthorFirstName());
        comment.setAuthorImage(commentDTO.getAuthorImage());
        comment.setCreatedAt(commentDTO.getCreatedAt());
        comment.setPk(commentDTO.getPk());
        comment.setText(commentDTO.getText());
        return comment;
    }

}
