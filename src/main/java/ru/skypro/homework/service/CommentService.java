package ru.skypro.homework.service;


import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentInfoDTO;


import java.util.List;

public interface CommentService {

    CommentDTO addCommentToAd(Long pk, CommentDTO commentDTO);

    List<CommentInfoDTO> getAllCommentsByPK(Long pk);

    String deleteCommentByAdAndCommentId(Long pk, Long commentId);

    CommentInfoDTO updateCommentAndGetInfo(Long adId, Long commentId, CommentDTO commentDTO);



}
