public abstract class AbstractContact {
// abstract Class for contacts (e.g. people, companies) to be added as entries in an Addressbook.
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
}
