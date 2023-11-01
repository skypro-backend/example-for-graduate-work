package ru.skypro.homework.service.mapping;

import ru.skypro.homework.dto.comment.Comment;
import ru.skypro.homework.dto.comment.Comments;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.CommentEntity;

import java.util.List;

public interface CommentMapper {
    public Comment commentEntityToCommentDto(CommentEntity commentcommentary);
    public List<Comment> listFromCommentEntityToDto(List<CommentEntity> inputCommentsList);
    public Comments adCommentsToCommentsDTO(Ad ad);
    public CommentEntity createOrUpdateCommentDtoToCommentEntity(CreateOrUpdateComment createOrUpdateComment);
}
