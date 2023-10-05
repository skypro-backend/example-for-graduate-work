package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Comments;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    Comments findByNameLike(String text);

    Comments save();
    Comments getById();
    List<Comments> findByTextLike(String comments);
    }


