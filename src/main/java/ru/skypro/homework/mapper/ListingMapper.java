package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.dto.listing.CreateOrUpdateListing;
import ru.skypro.homework.dto.listing.ExtendedListingDTO;
import ru.skypro.homework.dto.listing.ListingDTO;
import ru.skypro.homework.dto.listing.ListingsDTO;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.Listing;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ListingMapper {

    String address = "/listings/image";

    @Mapping(target = "image", ignore = true)
    @Mapping(target = "author.id", source = "author")
    @Mapping(target = "id", source = "pk")
    Listing listingDTOToListing(ListingDTO dto);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "image", source = "image", qualifiedByName = "imageToString")
    ListingDTO listingToListingDTO(Listing entity);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "image", source = "image", qualifiedByName = "imageToString")
    ExtendedListingDTO toExtendedListing(Listing entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "image", ignore = true)
    Listing createOrUpdateListingToListing(CreateOrUpdateListing dto);

    @Named("imageToString")
    default String imageToString(Image image) {
        if (image == null) {
            return null;
        }
        return address + image.getId();
    }

    default ListingsDTO listingListToListings(List<Listing> list) {
        ListingsDTO listingsDTO = new ListingsDTO();
        listingsDTO.setCount(list.size());
        List<ListingDTO> listingDtoList = new ArrayList<>();
        for (Listing listing : list) {
            listingDtoList.add(listingToListingDTO(listing));
        }
        listingsDTO.setResults(listingDtoList);
        return listingsDTO;
    }
}
