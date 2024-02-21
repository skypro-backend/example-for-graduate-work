package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;
import ru.skypro.homework.config.GetAuthentication;
import ru.skypro.homework.dto.listing.CreateOrUpdateListing;
import ru.skypro.homework.dto.listing.ExtendedListingDTO;
import ru.skypro.homework.dto.listing.ListingDTO;
import ru.skypro.homework.dto.listing.ListingsDTO;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.Listing;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.ListingMapper;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.ListingRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.ListingsService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListingsServiceImpl implements ListingsService {

    private final ListingRepository listingRepository;
    private final CommentRepository commentRepository;
    private final ImageRepository imageRepository;
    private final ListingMapper listingMapper;
    private final ImageService imageService;
    private Listing listing;
    private Authentication authentication;

    @Override
    public ListingDTO getAllListings() {
        List<Listing> listings = listingRepository.findAll();
        return listingMapper.listingToListingDTO(listings);
    }

    @Override
    public ListingDTO addListing(CreateOrUpdateListing createOrUpdateListing, MultipartFile image, Authentication authentication) throws IOException {
        Listing listing = listingMapper.createOrUpdateListingToListing(createOrUpdateListing);
        User user = new GetAuthentication().getAuthenticationUser(authentication.getName());
        listing.setUser(user);
        listingRepository.save(listing);
        listing.setImage(imageService.uploadImage(listing.getId(), image));
        listingRepository.save(listing);
        return listingMapper.listingToListingDTO(listing);
    }

    @Override
    public ExtendedListingDTO getListing(long id) {
        return listingMapper.toExtendedListing(listingRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Объявление с ID = " + id + " не найдено ")));
    }

    @Override
    @Transactional
    public void deleteListing(long id, Authentication authentication) throws AccessDeniedException {

        Listing listing = listingRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Объявление с ID" + id + "не найдено"));

        checkPermit(listing, authentication);
        commentRepository.deleteCommentsByListingId(id);
        imageRepository.deleteById(listing.getImage().getId());
        listingRepository.deleteById(id);
    }


    @Override
    public ListingDTO updateListing(long id, CreateOrUpdateListing createOrUpdateAd, Authentication authentication) throws AccessDeniedException {
        Listing listing = listingRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Объявление с ID" + id + "не найдено"));
        checkPermit(listing, authentication);
        listing.setTitle(createOrUpdateAd.getTitle());
        listing.setPrice(createOrUpdateAd.getPrice());
        listingRepository.save(listing);
        return listingMapper.listingToListingDTO(listing);
    }



    @Override
    public ListingsDTO getListingsMe(Authentication authentication) {
        User user = new GetAuthentication().getAuthenticationUser(authentication.getName());
        List<Listing> listingList = listingRepository.findListingByAuthorId(user.getId());
        return listingMapper.listingListToListings(listingList);
    }

    @Override
    @Transactional
    public void updateListingImage(Long id, MultipartFile image, Authentication authentication) throws IOException {
        Listing listing = listingRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Объявление с ID" + id + " не найдено"));
        checkPermit(listing, authentication);
        Image imageFile = listing.getImage();
        listing.setImage(imageService.uploadImage(listing.getId(), image));
        imageService.removeImage(imageFile);
        listingRepository.save(listing);
    }

    public void checkPermit(Listing listing, Authentication authentication) throws AccessDeniedException {
        if (!listing.getUser().getEmail().equals(authentication.getName())
                && !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            throw new AccessDeniedException("Вы не можете редактировать или удалять чужое объявление");
        }
    }


}