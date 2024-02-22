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


    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "user.id")
    @Mapping(target = "image", source = "image", qualifiedByName = "imageToString")
    ListingDTO listingToListingDTO(Listing entity);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "authorFirstName", source = "user.firstName")
    @Mapping(target = "authorLastName", source = "user.lastName")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "phone", source = "user.phone")
    @Mapping(target = "image", source = "image", qualifiedByName = "imageToString")
    ExtendedListingDTO toExtendedListing(Listing entity);



    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "image", ignore = true)
    Listing createOrUpdateListingToListing(CreateOrUpdateListing dto);

    @Named("imageToString")
    default String imageToString(Image image) {
        if (image == null) {
            return null;
        }
        return address + image.getId();
    }

    default ListingsDTO listingListToListings(List<Listing> list) { //без обратного метода
        ListingsDTO listingsDTO = new ListingsDTO();
        listingsDTO.setCount(list.size());
        List<ListingDTO> listingDTOList = new ArrayList<>();
        for (Listing listing : list) {
            listingDTOList.add(listingToListingDTO(listing));
        }
        listingsDTO.setResults(listingDTOList);
        return listingsDTO;
    }

}
