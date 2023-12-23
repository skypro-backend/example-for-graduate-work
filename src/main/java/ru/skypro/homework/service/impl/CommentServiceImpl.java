package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.mappers.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;


import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(AdRepository adRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    //Метод получения списка всех комментариев объявления
    @Override
    public CommentsDTO getComments(Integer adId) {
        log.info("Getting comments for ad with ID: {}", adId);
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);               // Поиск объявления в репозитории по его ID
        List<CommentDTO> commentDTOList = new ArrayList<>();                                     // Инициализация списка CommentDTO для хранения результатов
        for (Comment comment : commentRepository.findAllByAd(ad)) {                                // Получение всех комментариев данного объявления из репозитория
            commentDTOList.add(CommentMapper.INSTANCE.toCommentDTO(comment, comment.getAuthor())); // Преобразование каждого комментария в CommentDTO с использованием маппера
        }
        CommentsDTO commentsDTO = new CommentsDTO();                           // Создание объекта CommentsDTO, который будет содержать список CommentDTO
        commentsDTO.setResults(commentDTOList);                               // Установка списка CommentDTO в объект CommentsDTO
        commentsDTO.setCount(commentDTOList.size());                          // Установка общего количества комментариев для данного объявления в объект CommentsDTO
        commentsDTO.setCount(commentDTOList.size());                          // Возвращение объекта CommentsDTO, содержащего список комментариев для данного объявления и их общее количество
        return commentsDTO;
    }

    //Метод добавления комментария к объявлению
    @Override
    public CommentDTO addComment(Integer adId, CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {
        log.info("Adding comment for ad with ID: {}", adId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();  // Получение информации о текущем пользователе из контекста безопасности
        User user = userRepository.findByEmail(authentication.getName());                        // Используем имя пользователя из аутентификации для поиска соответствующего пользователя в репозитории
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);              // Поиск объявления в репозитории по его ID
        Comment comment = new Comment();                                                         // Создание нового комментария
        comment.setAd(ad);                                                                      // Установка объявления для нового комментария
        comment.setAuthor(user);                                                                // Установка автора комментария
        comment.setText(createOrUpdateCommentDTO.getText());                                    // Установка текста комментария из запроса
        comment.setCreatedAt(System.currentTimeMillis());                                      // Установка времени создания комментария в миллисекундах
        return CommentMapper.INSTANCE.toCommentDTO(commentRepository.save(comment), user);     // Сохранение нового комментария в репозитории и преобразование его в CommentDTO с использованием маппера
    }

    //Метод удаления комментария
    @Override
    public Void deleteComment(Integer adId, Integer commentId) {
        log.info("Deleting comment with ID: {} for ad with ID: {}", commentId, adId);
        commentRepository.deleteById(commentId);             // Удаление комментария из репозитория по его ID
        return null;                                         // Возвращение null, поскольку метод возвращает Void
    }

    @Override
    public CommentDTO patchComment(Integer adId, Integer commentId, CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {
        log.info("Updating comment with ID: {} for ad with ID: {}", commentId, adId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName());
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        comment.setText(createOrUpdateCommentDTO.getText());
        return CommentMapper.INSTANCE.toCommentDTO(commentRepository.save(comment),user);
    }
}
