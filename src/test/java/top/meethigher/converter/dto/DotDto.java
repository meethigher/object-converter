package top.meethigher.converter.dto;

public class DotDto {

    private String mingCheng;

    private String superMingCheng;

    private String superSkill;

    public String getMingCheng() {
        return mingCheng;
    }

    public void setMingCheng(String mingCheng) {
        this.mingCheng = mingCheng;
    }

    public String getSuperSkill() {
        return superSkill;
    }

    public void setSuperSkill(String superSkill) {
        this.superSkill = superSkill;
    }

    public String getSuperMingCheng() {
        return superMingCheng;
    }

    public void setSuperMingCheng(String superMingCheng) {
        this.superMingCheng = superMingCheng;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"mingCheng\":\"")
                .append(mingCheng).append('\"');
        sb.append(",\"superMingCheng\":\"")
                .append(superMingCheng).append('\"');
        sb.append(",\"superSkill\":\"")
                .append(superSkill).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
