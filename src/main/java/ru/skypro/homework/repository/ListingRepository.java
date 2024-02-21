package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.Listing;

import java.util.List;

public interface ListingRepository extends JpaRepository<Listing, Long> {
    List<Listing> findListingByAuthorId(long id);

}
