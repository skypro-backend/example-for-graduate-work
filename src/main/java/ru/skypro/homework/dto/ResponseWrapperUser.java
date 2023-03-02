package ru.skypro.homework.dto;

import lombok.Data;
import java.util.Collection;

@Data
public class ResponseWrapperUser {
   private Integer count;
    // пока коллекцию поставила, вроде там не понятно сколько будет юзеров
   private Collection<User> results;
}
