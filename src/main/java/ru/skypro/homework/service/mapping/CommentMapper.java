package ru.skypro.homework.service.mapping;

import ru.skypro.homework.dto.comment.Comments;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;

import java.util.List;

public interface CommentMapper {
    public ru.skypro.homework.dto.comment.Comment commentEntityToCommentDto(Comment commentcommentary);
    public List<ru.skypro.homework.dto.comment.Comment> listFromCommentEntityToDto(List<Comment> inputCommentsList);
    public Comments adCommentsToCommentsDTO(Ad ad);
    public Comment createOrUpdateCommentDtoToCommentEntity(CreateOrUpdateComment createOrUpdateComment);
}
