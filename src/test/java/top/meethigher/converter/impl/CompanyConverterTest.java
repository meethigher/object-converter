package top.meethigher.converter.impl;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import top.meethigher.converter.ObjectConverter;
import top.meethigher.converter.annotation.ConverterResult;
import top.meethigher.converter.annotation.ConverterResults;
import top.meethigher.converter.annotation.ConverterResultsMap;
import top.meethigher.converter.dto.CompanyDto;
import top.meethigher.converter.entity.Company;
import top.meethigher.converter.proxy.ConverterProvider;

import java.util.Date;
import java.util.LinkedList;

/**
 * 无继承多层级对象转换测试
 *
 * @author chenchuancheng
 * @since 2022/10/24 10:56
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CompanyConverterTest {

    interface CompanyConverter extends ObjectConverter<Company, CompanyDto> {

        @ConverterResults(id = "company-companyDto", value = {
                @ConverterResult(from = "companyName", to = "gongSiMingCheng"),
                @ConverterResult(from = "companyLoc", to = "gongSiDiZhi"),
                @ConverterResult(from = "companyArea", to = "gongSiMianJi"),
        })
        @ConverterResultsMap("default")
        @Override
        CompanyDto convert(Company company);

        @ConverterResults(@ConverterResult(from = "personList", to = "renYuanLieBiao"))
        @ConverterResultsMap("company-companyDto")
        @Override
        Company reverse(CompanyDto companyDto);
    }


    private final static CompanyConverter converter = ConverterProvider.defaultInstance(CompanyConverter.class);

    private static Company company = create();


    private static CompanyDto companyDto;

    public static Company create() {
        Company company = new Company();
        company.setCompanyName("测试公司");
        company.setCompanyArea(200.0);
        company.setCompanyLoc("测试地址");
        company.setCreateTime(new Date());
        company.setPersonList(new LinkedList<>());
        company.getPersonList().add(PersonConverterTest.create());
        return company;
    }

    @Test
    @Order(1)
    void convert() {
//        companyDto = converter.convert(new Company());
        companyDto = converter.convert(create());
        System.out.println("正向转换:" + companyDto);
    }

    @Test
    @Order(2)
    void reverse() {
        company = converter.reverse(companyDto);
        System.out.println("逆向转换:" + company);
    }
}
