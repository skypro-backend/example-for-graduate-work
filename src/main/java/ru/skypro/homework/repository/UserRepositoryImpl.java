package ru.skypro.homework.repository;

import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class UserRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void updateUserEnabled(int userId, boolean enabled) {
        User user = entityManager.find(User.class, userId);
        if (user != null) {
            user.setEnabled(enabled);
            entityManager.merge(user);
        }
    }
}
