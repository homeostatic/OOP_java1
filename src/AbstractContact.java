public abstract class AbstractContact {
// abstract Class for contacts (e.g. people, companies) to be added as entries in an Addressbook.
    private Address address;

    Address getAddress() {
        return address;
    }

    void setAddress(Address address) {
        this.address = address;
    }

    public abstract String toString();
    
}
