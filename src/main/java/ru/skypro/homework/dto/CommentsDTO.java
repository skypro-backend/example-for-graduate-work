package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class CommentsDTO {
    private int count;
    private List<CommentDTO> results;

    public static CommentsDTO of(List<CommentDTO> results) {
        CommentsDTO responseWrapperCommentDTO = new CommentsDTO();
        if (results == null) {
            return responseWrapperCommentDTO;
        }
        responseWrapperCommentDTO.results = results;
        responseWrapperCommentDTO.count = results.size();
        return responseWrapperCommentDTO;
    }
}
