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
    public Address(String street, int houseNumber, String city, int postcode){
        // all data stored as strings to account for anomalous addresses eg: houseNumber:42a or postcode:E1 7HT
        this.street = street;
        this.houseNumber = String.valueOf(houseNumber);
        this.city = city;
        this.postcode = String.valueOf(postcode);
    }
    public String toString(){
        // Standard german Format
        return String.format("%s %s, %s %s", this.street,this.houseNumber,this.postcode,this.city);
    }
    public String getStreet() {
        return street;
    }
    public String getHouseNumber() {
        return houseNumber;
    }
    public String getCity() {
        return city;
    }
    public String getPostcode() {
        return postcode;
    }
    
}
