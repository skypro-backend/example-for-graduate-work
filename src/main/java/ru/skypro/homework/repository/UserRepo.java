package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.projections.ExtendedAd;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Integer> {

    Optional<UserModel> findByUserName(String userName);

// получение информации по объявлению
    @Query("SELECT new ru.skypro.homework.projections." +
            "ExtendedAd(a.pk, u.firstName, u.lastName, a.description," +
            " u.userName, a.image, u.phone, a.price, a.title) " +
            "FROM UserModel u JOIN FETCH AdModel a ON a.userModel.id = u.id AND a.pk = :id")
    Optional<ExtendedAd> getExtendedAd(@Param("id") int id);
}
