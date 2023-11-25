package ru.skypro.homework.service.impl;
import org.springframework.stereotype.Service;

import ru.skypro.homework.projections.Comments;
import ru.skypro.homework.projections.CreateOrUpdateComment;

import ru.skypro.homework.service.CommentsService;

import javax.transaction.Transactional;
@Transactional
@Service
public class CommentsServiceImpl implements CommentsService {

        @Override
        public Comments getComments(int id) {
            return null;
        }

        @Override

            public Comments addComment(int id) {
                return null;
            }

            @Override
                public void deleteComment(int id, int commentsId) {

                }

                @Override

                    public CreateOrUpdateComment updateComment(int id, int commentsId, CreateOrUpdateComment createOrUpdateComment) {
                        return null;
                    }


}