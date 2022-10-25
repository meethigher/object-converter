package top.meethigher.converter.dto;

public class SuperPersonDto extends PersonDto {

    private String skill;

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"mingCheng\":\"")
                .append(super.getMingCheng()).append('\"');
        sb.append(",\"nianLing\":")
                .append(super.getNianLing());
        sb.append(",\"shengRi\":\"")
                .append(super.getShengRi()).append('\"');
        sb.append(",\"qian\":")
                .append(super.getQian());
        sb.append(",\"gender\":")
                .append(super.getGender());
        sb.append(",\"skill\":\"")
                .append(skill).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
