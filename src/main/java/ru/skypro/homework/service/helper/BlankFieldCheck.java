package ru.skypro.homework.service.helper;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class BlankFieldCheck {
    public static boolean blankFieldCheck(String input) {

        Pattern regex = Pattern.compile(".*[a-zA-Zа-яА-Я0-9\\s\\n].*");

        Matcher matcher = regex.matcher(input);

        return matcher.matches();
    }
}
