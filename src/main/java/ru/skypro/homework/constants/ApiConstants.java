package ru.skypro.homework.constants;

public final class ApiConstants {

    private ApiConstants() {
    }

    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    public static final String PHONE_PATTERN = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}";

    public static final int USERNAME_LENGTH = 32;
    public static final int PASSWORD_LENGTH = 255; // хранится хешированный пароль, не сырой
    public static final int NAME_LENGTH = 16;
    public static final int PHONE_LENGTH = 20;
    public static final int ROLE_LENGTH = 20;
    public static final int IMAGE_PATH_LENGTH = 255;
    public static final int AD_TITLE_LENGTH = 32;
    public static final int AD_DESCRIPTION_LENGTH = 64;
    public static final int COMMENT_TEXT_LENGTH = 64;

    public static final String ROLE_PREFIX = "ROLE_";
    public static final String IMAGES_URL_PREFIX = "/images/";
}
