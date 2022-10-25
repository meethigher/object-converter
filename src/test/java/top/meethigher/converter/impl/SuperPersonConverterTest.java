package top.meethigher.converter.impl;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import top.meethigher.converter.ObjectConverter;
import top.meethigher.converter.annotation.ConverterResult;
import top.meethigher.converter.annotation.ConverterResults;
import top.meethigher.converter.annotation.ConverterResultsMap;
import top.meethigher.converter.dto.SuperPersonDto;
import top.meethigher.converter.entity.SuperPerson;
import top.meethigher.converter.proxy.ConverterProvider;

import java.util.Date;

/**
 * 有继承单层级转换测试
 *
 * @author chenchuancheng
 * @since 2022/10/24 10:57
 */
class SuperPersonConverterTest {

    interface SuperPersonConverter extends ObjectConverter<SuperPerson, SuperPersonDto> {

        @ConverterResults(id = "person-personDto", value = {
                @ConverterResult(from = "name", to = "mingCheng"),
                @ConverterResult(from = "age", to = "nianLing"),
                @ConverterResult(from = "birth", to = "shengRi"),
        })
        @ConverterResultsMap("default")
        @Override
        SuperPersonDto convert(SuperPerson person);


        @ConverterResults(@ConverterResult(from = "money", to = "qian"))
        @ConverterResultsMap("person-personDto")
        @Override
        SuperPerson reverse(SuperPersonDto personDto);
    }

    private static SuperPerson person = create();

    private static SuperPersonDto personDto;

    private final static SuperPersonConverter converter = ConverterProvider.defaultInstance(SuperPersonConverter.class);


    public static SuperPerson create() {
        SuperPerson person = new SuperPerson();
        person.setAge(18);
        person.setMoney(2000.0);
        person.setName("八戒");
        person.setBirth(new Date());
        person.setGender(true);
        person.setSkill("横扫千军");
        return person;
    }

    @Test
    @Order(1)
    void convert() {
        personDto = converter.convert(person);
        System.out.println("正向转换:" + personDto);
    }

    @Test
    @Order(2)
    void reverse() {
        person = converter.reverse(personDto);
        System.out.println("逆向转换:" + person);
    }
}
