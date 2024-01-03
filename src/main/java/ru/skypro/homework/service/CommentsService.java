package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.mapping.CommentMapper;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.utils.CommentsDtoFound;
import ru.skypro.homework.repository.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <h2>CommentsService</h2>
 * Service managing comments added to advertisements by users
 */
@Service
@RequiredArgsConstructor
public class CommentsService {
    /**
     * Repository for comments stored in database
     */
    final private CommentRepository commentRepository;

    public CommentsDtoFound findCommentsRelatedToAd(int id) {
        CommentsDtoFound foundDtoWithResponseHttpStatus = new CommentsDtoFound();

        List<Comment> listOfComments = commentRepository.findByCreatedAt(id);

        CommentsDto commentsDto = new CommentsDto();

        commentsDto.setCount(listOfComments.size());
        commentsDto.setResults(
                listOfComments.stream()
                        .map(CommentMapper.INSTANCE::commentToDto)
                        .collect(Collectors.toList()));
        foundDtoWithResponseHttpStatus.setCommentsDto(commentsDto);
        if (commentsDto.getResults().isEmpty()) {
            foundDtoWithResponseHttpStatus.setHttpStatus(HttpStatus.NOT_FOUND);
        } else {
            foundDtoWithResponseHttpStatus.setHttpStatus(HttpStatus.OK);
        }
        return foundDtoWithResponseHttpStatus;
    }


}
