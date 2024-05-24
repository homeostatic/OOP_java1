public class Address {
    private String street;
    private String houseNumber;
    private String city;
    private String postcode;
    public Address(String street, String houseNumber, String city, String postcode){
        // all data stored as strings to account for anomalous addresses eg: houseNumber:42a or postcode:E1 7HT
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.postcode = postcode;
    }
    public String toString(){
        // Standard german Format
        return String.format("%s %s, %s %s", this.street,this.houseNumber,this.postcode,this.city);
    }
}
