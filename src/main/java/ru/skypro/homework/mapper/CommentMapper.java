package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;

public class CommentMapper {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    public ImageMapper(AdRepository adRepository,
                       UserRepository userRepository,
                       ImageRepository imageRepository) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
    }
    public CommentDTO mapToDTO(Comment comment) {
        return CommentDTO(
            comment.getId(),
            comment.getAuthorFirstName(),
            comment.getCreatedAt(),
            comment.getText(),
            comment.getAd().getId(),
            comment.getUser().getId(),
            comment.getImage().getId()
        );
    }

    public Comment mapToEntity(CommentDTO commentDTO) {
        return Comment(
                commentDTO.getId(),
                commentDTO.getAuthorFirstName(),
                commentDTO.getCreatedAt(),
                commentDTO.getText(),
                adRepository.findById(commentDTO.getAdId()),
                userRepository.findById(commentDTO.getUserId()),
                imageRepository.findById(commentDTO.getUserAvatarId())
        );
    }
}
