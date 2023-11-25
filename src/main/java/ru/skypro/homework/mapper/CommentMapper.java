package ru.skypro.homework.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
@Component
@RequiredArgsConstructor
public class CommentMapper {

//    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;





    public CommentDTO mapToDTO(Comment comment) {
        return new CommentDTO(
            comment.getId(),
            comment.getUserFirstName(),
            comment.getCreatedAt(),
            comment.getText(),
//            comment.getAd().getAuthor(),
            comment.getUser().getId(),
            comment.getImage().getId()
        );
    }

    public Comment mapToEntity(CommentDTO commentDTO) {


        return new Comment(

                commentDTO.getAuthorFirstName(),
                commentDTO.getCreatedAt(),
                commentDTO.getText(),
//                adRepository.getById(commentDTO.getAdId()),
                userRepository.getById(commentDTO.getUserId()),
                imageRepository.getById(commentDTO.getUserAvatarId())
        );

    }
}
