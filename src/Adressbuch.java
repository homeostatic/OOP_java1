import java.util.*;

public class Adressbuch {
	// class for saving an array of AbstractContact objects
	// includes methods for creation, recall, deletion and search with interactive console tools 
	
	private ArrayList<AbstractContact> adressbuch;
	
	Adressbuch(){
		// empty Construstor		
		this.adressbuch = new ArrayList<AbstractContact>();
			
	}
	Adressbuch(ArrayList<AbstractContact> array){
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
		
		
		System.out.println("Which of the following entries do you want to delete (-1 for cancel)");
		this.printContacts();
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
	
		
	public void addContact(Scanner scan){
		// methode for adding entries to the addressbook via interactive console inputs
		// accepts the Scanner object created in the main class to read user input
		System.out.println("What Kind of contact do you want to add? \n 1: Person \n 2: Company \n 3: Exit");
		Integer input = Integer.valueOf(scan.nextLine());
		if (input==1){
			
			Contact contact = Contact.scanContact(scan);
			this.adressbuch.add(contact);
			System.out.println("Contact successfully added! ");
		}
		else if(input==2){
			Firma firma = Firma.scanFirma(scan);
			this.adressbuch.add(firma);
			System.out.println("Firma added successfully! ");
		}
		else{
		System.out.println("\n Exiting... \n");
	}	
	 			

	}
	
	
	public void search(String s) {
	// checks name, city and street for match against a search string and prints results to console
		System.out.println(String.format("Searching for '%s' resulted in the following contacts:", s));
		Boolean anyFound = false;
		for (int i=0;i < this.adressbuch.size();i++){
		// for each contact:
			boolean found = false;
			AbstractContact cur_contact = this.adressbuch.get(i);
			// check name
			String cur_name = cur_contact.toString();
			if (cur_name.contains(s)){
				found = true;
			};
			// after all the checks results with a match will be printed to the console
			if (found){
				anyFound = true;
				System.out.println("Entry " + i + ":");
				System.out.println(cur_contact.toString());
			}
		
		}
		if (!anyFound){
			System.out.println(String.format("No results for '%s' found.", s));
		}
		
	}
	
		
	
		
	

}
