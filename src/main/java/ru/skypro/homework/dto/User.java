package ru.skypro.homework.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

  Integer id;
  String firstName;
  String lastName;
  String phone;
  String email;


}
