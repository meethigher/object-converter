package top.meethigher.converter.entity;

/**
 * 超人
 *
 * @author chenchuancheng
 * @since 2022/10/24 10:18
 */
public class SuperPerson extends Person {

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
        sb.append("\"name\":\"")
                .append(super.getName()).append('\"');
        sb.append(",\"age\":")
                .append(super.getAge());
        sb.append(",\"birth\":\"")
                .append(super.getBirth()).append('\"');
        sb.append(",\"money\":")
                .append(super.getMoney());
        sb.append(",\"gender\":")
                .append(super.getGender());
        sb.append(",\"skill\":\"")
                .append(skill).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
