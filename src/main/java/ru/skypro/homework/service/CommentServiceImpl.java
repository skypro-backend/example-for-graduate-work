package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentInfoDTO;
import ru.skypro.homework.pojo.Ad;
import ru.skypro.homework.pojo.Comment;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;

    public CommentServiceImpl(CommentRepository commentRepository, AdRepository adRepository) {
        this.commentRepository = commentRepository;
        this.adRepository = adRepository;
    }

    @Override
    public CommentDTO addCommentToAd(Long pk, CommentDTO commentDTO) {
        Ad ad = adRepository.findById(pk).orElse(null);
        if (ad != null) {
            Comment comment = new Comment();
            comment.setText(commentDTO.getText());
            comment.setUserId(ad.getUser().getUserID());
            comment.setPk(pk);
            comment.setTimeStamp(comment.getTimeStamp());

            Comment savedComment = commentRepository.save(comment);

            // Преобразование savedComment обратно в CommentDTO и его возврат
            CommentDTO createdCommentDTO = new CommentDTO();
            createdCommentDTO.setText(savedComment.getText());


            return createdCommentDTO;
        }
        return null;
    }

    @Override
    public List<CommentInfoDTO> getAllCommentsByPK(Long pk) {
        List<Comment> comments = commentRepository.findByPk(pk); // Получаем комментарии из базы данных
        List<CommentInfoDTO> commentsFullInfo = new ArrayList<>();

        for (Comment comment : comments) {
            CommentInfoDTO commentInfo = new CommentInfoDTO();
            commentInfo.setUserName(comment.getUser().getUserName());
            commentInfo.setAuthorImage(comment.getUser().getImage().getImagePath());
            commentInfo.setFirstName(comment.getUser().getFirstName());
            commentInfo.setCreatedAt(comment.getTimeStamp());
            commentInfo.setPk(comment.getPk());
            commentInfo.setText(comment.getText());

            commentsFullInfo.add(commentInfo);
        }

        return commentsFullInfo;
    }

    @Override
    public String deleteCommentByAdAndCommentId(Long pk, Long commentId) {
        Comment comment = commentRepository.findByPkAndCommentId(pk, commentId);

        if (comment != null) {
            commentRepository.delete(comment); // Удаляем комментарий из базы данных
            return "Комментарий успешно удален";
        } else {
            return "Комментарий не найден";
        }
    }

    @Override
    public CommentInfoDTO updateCommentAndGetInfo(Long pk, Long commentId, CommentDTO commentDTO) {
        Comment comment = commentRepository.findByPkAndCommentId(pk, commentId);

        if (comment != null) {
            // Обновляем текст комментария
            comment.setText(commentDTO.getText());

            // Сохраняем обновленный комментарий в бд
            comment = commentRepository.save(comment);

            // Преобразовываем comment в CommentInfoDTO
            CommentInfoDTO commentInfoDTO = new CommentInfoDTO();
            commentInfoDTO.setUserName(comment.getUser().getUserName());
            commentInfoDTO.setAuthorImage(comment.getUser().getImage().getImagePath());
            commentInfoDTO.setFirstName(comment.getUser().getFirstName());
            commentInfoDTO.setCreatedAt(comment.getTimeStamp());
            commentInfoDTO.setPk(comment.getCommentId());
            commentInfoDTO.setText(comment.getText());

            return commentInfoDTO;
        }

        return null; // Вернуть null, исключение потом допилю
    }

}



