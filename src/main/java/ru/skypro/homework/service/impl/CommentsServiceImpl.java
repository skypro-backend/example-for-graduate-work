package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
<<<<<<< HEAD
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
=======
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
>>>>>>> origin/Change
import ru.skypro.homework.service.CommentsService;

import javax.transaction.Transactional;


@Transactional
@Service
public class CommentsServiceImpl implements CommentsService {

    @Override
<<<<<<< HEAD
    public CommentsDTO getFullComments(int id) {
=======
    public Comments getFullComments(int id) {
>>>>>>> origin/Change
        return null;
    }

    @Override
<<<<<<< HEAD
    public CommentsDTO addComments(int id) {
=======
    public Comments addComments(int id) {
>>>>>>> origin/Change
        return null;
    }

    @Override
    public void removeComments(int id, int commentsId) {

    }

    @Override
<<<<<<< HEAD
    public CreateOrUpdateCommentDTO updateComments(int id, int commentsId) {
=======
    public CreateOrUpdateComment updateComments(int id, int commentsId) {
>>>>>>> origin/Change
        return null;
    }


}
