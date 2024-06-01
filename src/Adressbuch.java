import java.util.*;

public class Adressbuch {
	// class for saving an array of AbstractContact objects
	// includes methods for creation, recall, deletion and search with interactive console tools 
	
	private ArrayList<AbstractContact> adressbuch;
	
	public Adressbuch(){
		// empty Construstor		
		this.adressbuch = new ArrayList<AbstractContact>();
			
	}
	public Adressbuch(ArrayList<AbstractContact> array){
		// 2nd Constructor which accepts an Array of contacts (mainly for testing purposes)
		this.adressbuch = array;
	}
	
	
	public void printContacts() {
		// prints a list of saved contacts with entry numbers to the console		
		int len = this.adressbuch.size();
		
		for (int i=0; i < len; i++) {
			
			System.out.println("Entry " + i + ":");
			
			System.out.println(this.adressbuch.get(i).toString());
			
			
		}
	}	
	
	public void deleteContact(Scanner scan) {
		// interactive console tool for the deletion of a user selected Entry in the Addressbook
		// accepts the Scanner object created in the main class to read user console inputs
		
		this.printContacts();
		System.out.println("Which of the following entries do you want to delete (-1 for cancel)");
		String s = scan.nextLine();

		int s1 = Integer.parseInt(s);
		
		if(s1 == -1) {
			
			 return;
		}
		
		if (s1 > this.adressbuch.size() - 1) {
			System.out.println("Entry does not exist!");
			this.deleteContact(scan);
			
		}
		
		else {
			adressbuch.remove(s1);
			
		}
		
	}
	public void addContact(Contact contact) 
	 {	
		this.adressbuch.add(contact);
	 }
		
	public void adder_tool(Scanner scan){
		// methode for adding entries to the addressbook via interactive console inputs
		// accepts the Scanner object created in the main class to read user input
		System.out.println("What Kind of contact do you want to add? \n 1: Person \n 2: Company");
		if (Integer.valueOf(scan.nextLine())==1){
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
			
			int zipcodeInt = 0;
			String zipcode = scan.nextLine();
			if (zipcode != "") {
				zipcodeInt = Integer.parseInt(zipcode);
			}
			
			System.out.println("Enter a street");
			String street = scan.nextLine();
			System.out.println("Enter the number of the house:");
			String number = scan.nextLine();
			int numberInt = 0;
			if (number != "") {
				numberInt = Integer.parseInt(number);
			}
			Address address = new Address(city, zipcodeInt, street, numberInt);
			Contact contact = new Contact(name, address);
			this.adressbuch.add(contact);
		}
		if(Integer.valueOf(scan.nextLine())==2){
			// Step 1: Founder details
			
			// Step 2: Company Name

			//Step 3: Company Address
			;
		}
		//if (//another int??){}
			//error/try again	
	 
	// 			

	}
	
	
	public void search(String s) {
	// checks name, city and street for match against a search string and prints results to console
		System.out.println(String.format("Searching for '%s' resulted in the following contacts:", s));
		for (int i=0;i < this.adressbuch.size();i++){
		// for each contact:
			boolean found = false;
			AbstractContact cur_contact = this.adressbuch.get(i);
			// check name
			Name cur_name = cur_contact.getName();
			if (cur_name.firstname().contains(s)){
				found = true;
			};
			if (cur_name.lastname().contains(s)){
				found = true;
			}
			// check address
			Address cur_add = cur_contact.getAddress();
			if (cur_add.getCity().contains(s)){
				found = true;
			}
			if (cur_add.getStreet().contains(s)){
				found = true;
			}
			// after all the checks results with a match will be printed to the console
			if (found){
				System.out.println("Entry " + i + ":");
				System.out.println(cur_contact.toString());
			}
		
		}
		
	}
	
		
	
		
	

}
