package ru.skypro.homework.mapper;


import java.util.Collection;
import org.mapstruct.Mapper;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.record.AdRecord;

/**
 * маппер для {@link Ad} готовый рекорд {@link ru.skypro.homework.record.AdRecord}
 */

@Mapper(componentModel = "spring")
public interface AdMapper {

  Ad toEntity(AdRecord adRecord);

  AdRecord toRecord(Ad ad);

  Collection<Ad> toEntityList(Collection<AdRecord> adRecords);

  Collection<AdRecord> toRecordList(Collection<Ad> ads);
}
