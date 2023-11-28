package ru.skypro.homework.mappers;

import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.models.Comment;
import ru.skypro.homework.models.Comments;

public class CommentsMapper {

    public static Comments toComments(CommentsDto commentsDto){
        if(commentsDto == null){
            throw new NullPointerException("Tried to map null in Comments");
        }

        Comments comments = new Comments();

        comments.setCount(commentsDto.getCount());

        return comments;
    }

    public static CommentsDto fromComments(Comments comments){
        if(comments == null){
            throw new NullPointerException("Tried to map null in CommentsDto");
        }

        CommentsDto commentsDto = new CommentsDto();

        commentsDto.setCount(comments.getCount());

        return commentsDto;
    }



}
