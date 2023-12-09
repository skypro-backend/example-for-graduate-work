package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.models.Comment;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.AuthProvider;
import ru.skypro.homework.service.CommentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AuthProvider authProvider;
    private final CommentMapper mapper;

    @Override
    public CommentsDto getCommentsByAdId(Integer adId) {
        List<CommentDto> comments = repository.findByAd_Id(adId).stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
        return CommentsDto.builder()
                .count(comments.size())
                .results(comments)
                .build();
    }

    @Transactional
    @Override
    public CommentDto addCommentToAd(Integer adId, CreateOrUpdateCommentDto dto) {
        return adRepository.findById(adId)
                .map(ad -> {
                    Comment comment = mapper.convertToEntity(dto);
                    comment.setAd(ad);
                    comment.setUser(userRepository.findAuthUserByEmail(authProvider.getUsername()));
                    return mapper.convertToDto(repository.save(comment));
                })
                .orElseThrow(CommentNotFoundException::new);
    }

    @Transactional
    @Override
    public void deleteComment(Integer id, Integer adId) {
        repository.findByIdAndAd_Id(id, adId)
                .ifPresentOrElse(comment -> repository.deleteById(comment.getId()), () -> {
                    throw new CommentNotFoundException();
                });
    }

    @Transactional
    @Override
    public CommentDto updateComment(Integer id, Integer adId, CreateOrUpdateCommentDto dto) {
        return repository.findByIdAndAd_Id(id, adId)
                .map(comment -> {
                    if (dto.getText() != null) {
                        comment.setText(dto.getText());
                        comment = repository.save(comment);
                    }
                    return mapper.convertToDto(comment);
                })
                .orElseThrow(CommentNotFoundException::new);
    }

    @Override
    public boolean existsCommentByIdAndUsername(Integer id, String username) {
        return repository.existsByIdAndUserEmail(id, username);
    }

}
