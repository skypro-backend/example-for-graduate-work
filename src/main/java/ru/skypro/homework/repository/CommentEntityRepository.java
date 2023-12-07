package ru.skypro.homework.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.CommentEntity;

public interface CommentEntityRepository extends JpaRepository<CommentEntity,Long > {
    void deleteById(Integer commentId);
}
