package top.meethigher.converter.dto;


import top.meethigher.converter.entity.Person;

import java.util.Date;
import java.util.List;

/**
 * 公司信息Model
 *
 * @author chenchuancheng
 * @since 2022/10/24 8:50
 */
public class CompanyDto {

    private String gongSiMingCheng;

    private String gongSiDiZhi;

    private Double gongSiMianJi;

    private Date createTime;

    private List<Person> renYuanLieBiao;

    public String getGongSiMingCheng() {
        return gongSiMingCheng;
    }

    public void setGongSiMingCheng(String gongSiMingCheng) {
        this.gongSiMingCheng = gongSiMingCheng;
    }

    public String getGongSiDiZhi() {
        return gongSiDiZhi;
    }

    public void setGongSiDiZhi(String gongSiDiZhi) {
        this.gongSiDiZhi = gongSiDiZhi;
    }

    public Double getGongSiMianJi() {
        return gongSiMianJi;
    }

    public void setGongSiMianJi(Double gongSiMianJi) {
        this.gongSiMianJi = gongSiMianJi;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Person> getRenYuanLieBiao() {
        return renYuanLieBiao;
    }

    public void setRenYuanLieBiao(List<Person> renYuanLieBiao) {
        this.renYuanLieBiao = renYuanLieBiao;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"gongSiMingCheng\":\"")
                .append(gongSiMingCheng).append('\"');
        sb.append(",\"gongSiDiZhi\":\"")
                .append(gongSiDiZhi).append('\"');
        sb.append(",\"gongSiMianJi\":")
                .append(gongSiMianJi);
        sb.append(",\"createTime\":\"")
                .append(createTime).append('\"');
        sb.append(",\"renYuanLieBiao\":")
                .append(renYuanLieBiao);
        sb.append('}');
        return sb.toString();
    }
}
