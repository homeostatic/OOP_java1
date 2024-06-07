import java.util.Scanner;

public class Address {
    private String street;
    private String houseNumber;
    private String city;
    private String postcode;

    Address(String street, String houseNumber, String city, String postcode){
        // all data stored as strings to account for anomalous addresses eg: houseNumber:42a or postcode:E1 7HT
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.postcode = postcode;
    }
    Address(String street, int houseNumber, String city, int postcode){
        // alternate constructor to allow parsing of integer type housenumber and zipcodes
        // all data stored as strings to account for anomalous addresses eg: houseNumber:42a or postcode:E1 7HT
        this.street = street;
        this.houseNumber = String.valueOf(houseNumber);
        this.city = city;
        this.postcode = String.valueOf(postcode);
    }
    

    static Address scanAddress(Scanner scan){
        // called from addContact()->scanContact() and addContact()->scanFirma
        // creates a new Address object based on user input, accepts a Scanner object als arg in order to sequentially read console

            System.out.println("What is the address of your contact?");

			System.out.println("Enter a city:");
			String city = scan.nextLine();

			System.out.println("Enter a zipcode");
			String zipcode = scan.nextLine();
			
			System.out.println("Enter a street");
			String street = scan.nextLine();

			System.out.println("Enter the number of the house:");
			String number = scan.nextLine();

            // calls regular Address class constructor
			Address address = new Address(city, zipcode, street, number);
            return address;
    }

    public String toString(){
        // Standard german Format
        return String.format("%s %s, %s %s", this.street,this.houseNumber,this.postcode,this.city);
    }
    String getStreet() {
        return street;
    }
    String getHouseNumber() {
        return houseNumber;
    }
    String getCity() {
        return city;
    }
    String getPostcode() {
        return postcode;
    }
    
}
