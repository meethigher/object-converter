package top.meethigher.converter.entity;

import java.util.Date;

/**
 * Person
 *
 * @author chenchuancheng
 * @since 2022/10/20 14:22
 */
public class Person {

    private String name;

    private Integer age;

    private Date birth;

    private Double money;

    private Boolean gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"age\":")
                .append(age);
        sb.append(",\"birth\":\"")
                .append(birth).append('\"');
        sb.append(",\"money\":")
                .append(money);
        sb.append(",\"gender\":")
                .append(gender);
        sb.append('}');
        return sb.toString();
    }
}
