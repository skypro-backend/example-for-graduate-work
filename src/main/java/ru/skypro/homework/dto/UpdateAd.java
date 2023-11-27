package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class UpdateAd {
    private static long author;
    private static String image;
    private static String title;
    private static Class<? extends UpdateAd> aClass;
    private static int pk;
    private static int price;

    public UpdateAd(Object pk, Object author, Object image, Object price, Object title) {
    }

    public static Object getPk() {
        return null;
    }

    public static Object getAuthor() {
        return author;
    }

    public static Object getImage() {
        return image;
    }

    public static Object getTitle() {
        return title;
    }

    public static Object getPrice() {
        return price;
    }

    public static void setClass(Class<? extends UpdateAd> aClass) {
        UpdateAd.aClass = aClass;
    }

    public static void setPk(Object pk) {
        UpdateAd.pk = (int) pk;
    }

    public static void setPrice(Object price) {
        UpdateAd.price = (int) price;
    }

    public static void setAuthor(Object author) {
        UpdateAd.author = (long) author;
    }

    public static void setImage(Object image) {
        UpdateAd.image = (String) image;
    }

    public static void setTitle(Object title) {
        UpdateAd.title = (String) title;
    }
}
