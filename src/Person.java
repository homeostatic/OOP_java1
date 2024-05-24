public class Person {
    private Name name;
    private Address address;
    public Person(Name name, Address address ){
        this.name = name;
        this.address = address;
    }

    public String toString() {
        return String.format("%s,%s", this.name.toString(), this.address.toString());
    }
}
