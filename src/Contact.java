import java.util.Scanner;

public class Contact extends AbstractContact {
    private Name name;

    public Contact(){
        // blank constructor which allows for the creation of blank contacts
    }
    public Contact(Name name, Address address ){
        // overloading constructor which allows for direct assignment of parameters
        this.setAddress(address);
        this.setName(name);
    }
    public static Contact contactScan(Scanner scan){
        // method for reading in parameters from User

        // Step 1: Name
		System.out.println("Enter the first name:");
		String f = scan.nextLine();
		System.out.println("Enter the last name:");
		String l = scan.nextLine();
		
		Name name = new Name(f,l);
		
        // Step 2: Address
		System.out.println("What is the address of your contact?");
		System.out.println("Enter a city:");
		String city = scan.nextLine();
		System.out.println("Enter a zipcode");
				
		String zipcode = scan.nextLine();
        //int zipcodeInt = 0;
		//if (zipcode != "") {                      //code to convert to int
		//	zipcodeInt = Integer.parseInt(zipcode);
		//}
		
		System.out.println("Enter a street");
		String street = scan.nextLine();
		System.out.println("Enter the number of the house:");
		String number = scan.nextLine();
		//int numberInt = 0;        //code to convert to int
		//if (number != "") {
		//	numberInt = Integer.parseInt(number);
		//}
			 
		Address address = new Address(city, zipcode, street, number);
		Contact contact = new Contact(name, address);

        return contact;
    }



    public String toString() {
        return String.format("%s, %s", this.name.toString() ,this.getAddress().toString());
    }

    public void setName(Name newName){
        this.name = newName;
    }
    public Name getName() {
        return name;
    }


}
