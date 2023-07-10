package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.ResponseWrapperComment;
import ru.skypro.homework.model.Comment;
@Service
public interface CommentService {


    ResponseWrapperComment getUserComments(int userId);

    CommentDTO addComment(int adId, CommentDTO commentDTO);
}
