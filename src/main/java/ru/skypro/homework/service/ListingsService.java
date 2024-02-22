package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.listing.CreateOrUpdateListing;
import ru.skypro.homework.dto.listing.ExtendedListingDTO;
import ru.skypro.homework.dto.listing.ListingDTO;
import ru.skypro.homework.dto.listing.ListingsDTO;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;

public interface ListingsService {

    List<ListingDTO> getAllListings();

    ListingsDTO addListing(CreateOrUpdateListing createOrUpdateListingDTO, MultipartFile image, Authentication authentication) throws IOException;

    ExtendedListingDTO getListing(long id);

    void deleteListing(long id, Authentication authentication) throws AccessDeniedException;

    ListingsDTO updateListing(long id, CreateOrUpdateListing createOrUpdateListing, Authentication authentication) throws AccessDeniedException;

    ListingsDTO getListingsMe(Authentication authentication);

    @Transactional
    void updateListingImage(Long id, MultipartFile image, Authentication authentication) throws IOException;

}