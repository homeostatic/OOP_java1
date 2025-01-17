import java.util.Scanner;

public class Contact extends AbstractContact {
    private Name name;
              // address is inherited from AbstractContact

    Contact(Name name, Address address ){
        // Constructor which allows for direct assignment of parameters
        this.setAddress(address);
        this.setName(name);
    }
    Contact(Name name){
        // Constructor which allows for name without an address
        
        this.setName(name);
    }

    
    /** 
     * @param scan
     * @return Contact
     */
    static Contact scanContact(Scanner scan){
        // accepts and passed Scanner object to and from respective sub-methods
        // method for reading in parameters from User and creating contacts

        // Step 1: Name
        Name name = Name.addName(scan);
				
        // Step 2: Address
        Address address = Address.scanAddress(scan);
	
		Contact contact = new Contact(name, address);

        return contact;
    }

    public String toString() {
        return String.format("%s, %s", this.name.toString() ,this.getAddress().toString());
    }

    void setName(Name newName){
        this.name = newName;
    }
    Name getName() {
        return name;
    }

}
