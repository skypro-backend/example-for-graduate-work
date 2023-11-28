package ru.skypro.homework.mappers;

import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.models.CreateOrUpdateComment;

public class CreateOrUpdateCommentMapper {

    public static CreateOrUpdateComment toCreateOrUpdateComment
            (CreateOrUpdateCommentDto createOrUpdateCommentDto){
        if(createOrUpdateCommentDto == null){
            throw new NullPointerException("Tried to map null in CreateOrUpdateComment");
        }

        CreateOrUpdateComment createOrUpdateComment = new CreateOrUpdateComment();

        createOrUpdateComment.setText(createOrUpdateCommentDto.getText());

        return createOrUpdateComment;
    }

    public static CreateOrUpdateCommentDto fromCreateOrUpdateComment
            (CreateOrUpdateComment createOrUpdateComment){
        if(createOrUpdateComment == null){
            throw new NullPointerException("tried to map null in CreateOrUpdateCommentDto");
        }

        CreateOrUpdateCommentDto createOrUpdateCommentDto = new CreateOrUpdateCommentDto();

        createOrUpdateCommentDto.setText(createOrUpdateComment.getText());

        return createOrUpdateCommentDto;
    }

}
