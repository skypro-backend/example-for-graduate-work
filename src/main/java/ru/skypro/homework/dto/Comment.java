package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Comment {
    private Integer author ;

    private String authorImage ;
    
    private String authorFirstName ;
    
    private Integer createdAt ;
    
    private Integer pk ;
    
    private String text ;
}
