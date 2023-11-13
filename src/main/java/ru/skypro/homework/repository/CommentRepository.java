package ru.skypro.homework.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.homework.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}
