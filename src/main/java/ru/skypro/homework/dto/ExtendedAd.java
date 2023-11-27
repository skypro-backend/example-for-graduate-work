package ru.skypro.homework.dto;

import lombok.Data;


@Data
public class ExtendedAd {
    private static long author;
    private static String image;
    private static String title;
    private static Class<? extends UpdateAd> aClass;
    private static int pk;
    private static int price;

    public ExtendedAd(Object pk, Object author, Object image, Object price, Object title) {
    }

    public static Object getAuthor() {
        return author;
    }

    public static Object getImage() {
        return image;
    }

    public static Object getPrice() {
        return price;
    }

    public static Object getTitle() {
        return title;
    }

    public static Object getPk() {
        return pk;
    }

    public static void setPk(Object pk) {
        ExtendedAd.pk = (int) pk;
    }

    public static void setPrice(Object price) {
    }

    public static void setAuthor(Object author) {
    }

    public static void setImage(Object image) {
    }

    public static void setTitle(Object title) {

    }
}
