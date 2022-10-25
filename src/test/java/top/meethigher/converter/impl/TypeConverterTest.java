package top.meethigher.converter.impl;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import top.meethigher.converter.ObjectConverter;
import top.meethigher.converter.annotation.ConverterResult;
import top.meethigher.converter.annotation.ConverterResults;
import top.meethigher.converter.annotation.ConverterResultsMap;
import top.meethigher.converter.dto.PersonDto;
import top.meethigher.converter.entity.Person;
import top.meethigher.converter.proxy.ConverterProvider;

import java.util.Date;

/**
 * 类型转换测试
 * 借鉴Spring-beans的BeanUtils.copy做法，类型不匹配直接忽略掉
 *
 * @author chenchuancheng
 * @since 2022/10/24 10:58
 */
class TypeConverterTest {

    interface TypeConverter extends ObjectConverter<Person, PersonDto> {

        @ConverterResults(id = "person-personDto", value = {
                @ConverterResult(from = "name", to = "nianLing"),
        })
        @ConverterResultsMap("default")
        @Override
        PersonDto convert(Person person);


        @ConverterResults(@ConverterResult(from = "money", to = "qian"))
        @ConverterResultsMap("person-personDto")
        @Override
        Person reverse(PersonDto personDto);

    }


    private static Person person = create();

    private static PersonDto personDto;

    private final static TypeConverter converter = ConverterProvider.defaultInstance(TypeConverter.class);


    public static Person create() {
        Person person = new Person();
        person.setAge(18);
        person.setMoney(2000.0);
        person.setName("八戒");
        person.setBirth(new Date());
        person.setGender(true);
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
