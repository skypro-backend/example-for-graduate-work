package ru.skypro.homework;

import lombok.Data;
import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.AdMapper;

public class AdTest {

    @Test
    public void test1() {
        AdDto ad = new AdDto();
        ad.setAuthor(1111);
        AdEntity adEntity = AdMapper.INSTANCE.adToAdEntity(ad);
        System.out.println("adEntity.getAuthor() = " + adEntity.getAuthor());
    }
}
