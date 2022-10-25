package top.meethigher.converter.impl;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import top.meethigher.converter.ObjectConverter;
import top.meethigher.converter.annotation.ConverterResult;
import top.meethigher.converter.annotation.ConverterResults;
import top.meethigher.converter.annotation.ConverterResultsMap;
import top.meethigher.converter.dto.DotDto;
import top.meethigher.converter.entity.Dot;
import top.meethigher.converter.entity.Person;
import top.meethigher.converter.entity.SuperPerson;
import top.meethigher.converter.proxy.ConverterProvider;

import java.util.Date;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DotConverterTest {

    interface DotConverter extends ObjectConverter<Dot, DotDto> {

        @ConverterResults(value = {
                @ConverterResult(from = "person.name", to = "mingCheng"),
                @ConverterResult(from = "superPerson.skill", to = "superSkill"),
                @ConverterResult(from = "superPerson.name", to = "superMingCheng")
        })
        @Override
        DotDto convert(Dot dot);

        @ConverterResultsMap("default")
        @Override
        Dot reverse(DotDto dotDto);
    }

    private final static DotConverter converter = ConverterProvider.dotSupportInstance(DotConverter.class);

    @Test
    @Order(1)
    void convert() {
        dotDto = converter.convert(create());
        System.out.println("正向转换:" + dotDto);
    }

    @Test
    @Order(2)
    void reverse() {
        dot = converter.reverse(dotDto);
        System.out.println("逆向转换:" + dot);
    }



    private static Dot dot = create();

    private static DotDto dotDto;

    public static Dot create() {
        Person person = new Person();
        person.setAge(18);
        person.setMoney(2000.0);
        person.setName("八戒");
        person.setBirth(new Date());
        person.setGender(true);
        Dot dot = new Dot();
        dot.setPerson(person);
        dot.setSuperPerson(new SuperPerson());
        dot.getSuperPerson().setSkill("横扫千军");
        dot.getSuperPerson().setName("悟空");
        return dot;
    }
}
