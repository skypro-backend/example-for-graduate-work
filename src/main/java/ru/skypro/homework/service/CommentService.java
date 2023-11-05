package ru.skypro.homework.service;

import ru.skypro.homework.dto.comment.Comment;
import ru.skypro.homework.dto.comment.Comments;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    public Comments getCommentsOfOneAd(int AdId);
    public Comment addCommentToAd(CreateOrUpdateComment commentToAdd, int adId);
    public boolean patchCommentByIdAndAdId(int adId, Integer commentId, CreateOrUpdateComment createOrUpdateComment, String username);
    public boolean deleteCommentByIdAndAdId(int adId, Integer commentId, String username);

}
