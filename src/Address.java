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
        // all data stored as strings to account for anomalous addresses eg: houseNumber:42a or postcode:E1 7HT
        this.street = street;
        this.houseNumber = String.valueOf(houseNumber);
        this.city = city;
        this.postcode = String.valueOf(postcode);
    }
    // package private
    static Address scanAddress(Scanner scan){
            System.out.println("What is the address of your contact?");
			System.out.println("Enter a city:");
			String city = scan.nextLine();
			System.out.println("Enter a zipcode");
			
			String zipcode = scan.nextLine();
            //int zipcodeInt = 0;
			//if (zipcode != "") {
			//	zipcodeInt = Integer.parseInt(zipcode);
			//}
			
			System.out.println("Enter a street");
			String street = scan.nextLine();
			System.out.println("Enter the number of the house:");
			String number = scan.nextLine();
			//int numberInt = 0;
			//if (number != "") {
			//	numberInt = Integer.parseInt(number);
			//}
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
