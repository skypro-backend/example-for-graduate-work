package ru.skypro.homework.dto;

import lombok.Data;
@Data
public class Ads {
        private static Object author;
        private static String image;
        private static String title;
        private static Class<? extends UpdateAd> aClass;
        private static int pk;
        private static int price;

    public Ads(Object pk, Object author, Object image, Object price, Object title) {
    }

    public static Object getPk() {
        return pk;
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
        return null;
    }

    public static void setPk(Object pk) {
        Ads.pk = (int) pk;
    }

    public static void setPrice(Object price) {
        Ads.price = (int) price;
    }

    public static void setAuthor(Object author) {
        Ads.author = author;
    }

    public static void setImage(Object image) {
        Ads.image = (String) image;
    }

    public static void setTitle(Object title) {
        Ads.title = (String) title;
    }
}
