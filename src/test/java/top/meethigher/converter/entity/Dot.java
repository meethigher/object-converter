package top.meethigher.converter.entity;

public class Dot {

    private Person person;

    private SuperPerson superPerson;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public SuperPerson getSuperPerson() {
        return superPerson;
    }

    public void setSuperPerson(SuperPerson superPerson) {
        this.superPerson = superPerson;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"person\":")
                .append(person);
        sb.append(",\"superPerson\":")
                .append(superPerson);
        sb.append('}');
        return sb.toString();
    }
}
