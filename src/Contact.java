public class Contact {
    private Name name;
    private Address address;

    public Contact(){
        // blank constructor which allows for the creation of blank contacts
    }
    public Contact(Name name, Address address ){
        // overloading constructor which allows for direct assignment of parameters
        this.setAddress(address);
        this.setName(name);
    }

    public String toString() {
        return String.format("%s, %s", this.name.toString(), this.address.toString());
    }
    public void setAddress(Address newAddress){
        this.address = newAddress;
    }
    public void setName(Name newName){
        this.name = newName;
    }

}
