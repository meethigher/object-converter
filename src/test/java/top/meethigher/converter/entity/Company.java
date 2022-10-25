package top.meethigher.converter.entity;

import java.util.Date;
import java.util.List;

/**
 * 公司信息
 *
 * @author chenchuancheng
 * @since 2022/10/24 8:48
 */
public class Company {

    private String companyName;

    private String companyLoc;

    private Double companyArea;

    private Date createTime;

    private List<Person> personList;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLoc() {
        return companyLoc;
    }

    public void setCompanyLoc(String companyLoc) {
        this.companyLoc = companyLoc;
    }

    public Double getCompanyArea() {
        return companyArea;
    }

    public void setCompanyArea(Double companyArea) {
        this.companyArea = companyArea;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"companyName\":\"")
                .append(companyName).append('\"');
        sb.append(",\"companyLoc\":\"")
                .append(companyLoc).append('\"');
        sb.append(",\"companyArea\":")
                .append(companyArea);
        sb.append(",\"createTime\":\"")
                .append(createTime).append('\"');
        sb.append(",\"personList\":")
                .append(personList);
        sb.append('}');
        return sb.toString();
    }
}
