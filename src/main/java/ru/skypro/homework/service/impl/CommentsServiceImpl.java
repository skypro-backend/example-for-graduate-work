package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.exceptions.AdNotFoundException;
import ru.skypro.homework.exceptions.CommentNotFoundException;
import ru.skypro.homework.exceptions.UserNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.model.CommentModel;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.projections.Comments;
import ru.skypro.homework.projections.CreateOrUpdateComment;
import ru.skypro.homework.repository.AdRepo;
import ru.skypro.homework.repository.CommentRepo;
import ru.skypro.homework.repository.UserRepo;
import ru.skypro.homework.service.CommentsService;
import ru.skypro.homework.service.until.Until;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.skypro.homework.mapper.CommentMapper.toCommentDTO;


@Service
//@Transactional
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final CommentRepo commentRepo;
    private final AdServiceImpl adService;
    private final AdRepo adRepo;
    private final UserRepo userRepo;


    /**
     * Поиск комментария
     */
    @Override
    public Comments getComments(int id) {
        CommentModel commentModel = commentRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        return CommentMapper.toComments(commentModel);
    }

    public Comments getAllComments(int adId) {
        adService.getAds(adId);
        List<CommentDTO> commentsDTOList = commentRepo.findAll().stream().map(CommentMapper::toCommentDTO).collect(Collectors.toList());
        return new Comments(commentsDTOList, commentsDTOList.size());
    }


    /**
     * Создание комментария
     */
    @Override
    public CommentDTO addComment(int id, CreateOrUpdateComment createOrUpdateComment, Authentication authentication) {
        UserModel user = Until.addUserFromRepo(authentication);

//        UserModel user = userRepo.findByUserName(authentication.getName())
//                .orElseThrow(UserNotFoundException::new);
        CommentModel commentModel = new CommentModel();
        commentModel.setCreateAt(LocalDateTime.parse(LocalDateTime.now()
                .format(DateTimeFormatter.ISO_DATE_TIME)));
        commentModel.setText(createOrUpdateComment.getText());
        commentModel.setUserModel(user);
        commentModel.setAdModel(adRepo.findById(id)
                .orElseThrow(AdNotFoundException::new));
        commentRepo.save(commentModel);
        return toCommentDTO(commentModel);
    }

    /**
     * Удаление коментария
     */
    @Override
    public void deleteComment(int id, int commentsId) {
        Optional<AdModel> adModel = adRepo.findById(id);
        commentRepo.deleteById(commentsId);
    }

    /**
     * Редактирование комментария
     */
    @Override
    public CreateOrUpdateComment updateComment(int id, int commentsId, CreateOrUpdateComment createOrUpdateComment) {
        Optional<AdModel> ad = adRepo.findById(id);
        if (ad.isPresent()) {
            Comments comments = getComments(commentsId);
            return CommentMapper.toCreateOrUpdateComment(comments);
        } else {
            throw new CommentNotFoundException();
        }
    }
}
