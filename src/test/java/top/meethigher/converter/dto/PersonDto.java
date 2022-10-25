package top.meethigher.converter.dto;

import java.util.Date;

/**
 * PersonDto
 *
 * @author chenchuancheng
 * @since 2022/10/20 14:22
 */
public class PersonDto {

    private String mingCheng;

    private Integer nianLing;

    private Date shengRi;

    private Double qian;

    private Boolean gender;

    public String getMingCheng() {
        return mingCheng;
    }

    public void setMingCheng(String mingCheng) {
        this.mingCheng = mingCheng;
    }

    public Integer getNianLing() {
        return nianLing;
    }

    public void setNianLing(Integer nianLing) {
        this.nianLing = nianLing;
    }

    public Date getShengRi() {
        return shengRi;
    }

    public void setShengRi(Date shengRi) {
        this.shengRi = shengRi;
    }

    public Double getQian() {
        return qian;
    }

    public void setQian(Double qian) {
        this.qian = qian;
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
        sb.append("\"mingCheng\":\"")
                .append(mingCheng).append('\"');
        sb.append(",\"nianLing\":")
                .append(nianLing);
        sb.append(",\"shengRi\":\"")
                .append(shengRi).append('\"');
        sb.append(",\"qian\":")
                .append(qian);
        sb.append(",\"gender\":")
                .append(gender);
        sb.append('}');
        return sb.toString();
    }
}
