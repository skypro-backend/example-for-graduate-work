package ru.skypro.homework.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseWrapperAds {

  Integer count;
  Collection<AdsDTO> results;

}
