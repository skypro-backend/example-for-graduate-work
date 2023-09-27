package ru.skypro.homework.service.comment;

import lombok.Data;
import org.springframework.stereotype.Service;
import ru.skypro.homework.repository.CommentRepository;

@Data
@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;

}